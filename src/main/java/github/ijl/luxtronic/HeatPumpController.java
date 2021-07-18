package github.ijl.luxtronic;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import github.ijl.luxtronic.config.v161.Calculations;
import github.ijl.luxtronic.config.v161.Parameters;
import github.ijl.luxtronic.exception.InvalidParameterException;
import github.ijl.luxtronic.format.FormatConverter;
import github.ijl.luxtronic.format.OneToOneConverter;
import github.ijl.luxtronic.param.DomesicHotWaterParameter;
import github.ijl.luxtronic.param.HeatingParameter;

@RestController
@RequestMapping("luxtronic")
public class HeatPumpController {
	private Logger mLog = LoggerFactory.getLogger(HeatPumpController.class);

	@Autowired
	private ApplicationContext mApplicationContext;
	@Autowired
	private HeatPumpSocketWrapper mHeatPumpSocketWrapper;

	/**
	 * To read parameters send 3003 0000 (0x00 0x00 0x0b 0xbb 0x00 0x00 0x00 0x00)
	 * The Luxtronik responds with the command (4 bytes) and the number of
	 * parameters that follow (4 bytes), also formatted big endian.
	 * 
	 * @return
	 */
	@RequestMapping(path = "/parameters", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, String> parameters() {
		mLog.debug("/parameters called!");
		final Map<String, String> result = getParameters(3003, false, 0);
		return result;
	}

	/**
	 * To read parameters send 3003 0000 (0x00 0x00 0x0b 0xbb 0x00 0x00 0x00 0x00)
	 * The Luxtronik responds with the command (4 bytes) and the number of
	 * parameters that follow (4 bytes), also formatted big endian.
	 * 
	 * @return
	 */
	@RequestMapping(path = "/parameter/{parameter}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String parameter(final @PathVariable("parameter") String pParameter) {
		mLog.debug("/parameter/" + pParameter + " called!");
		final Map<String, String> result = getParameters(3003, false, 0);
		return result.get(pParameter);
	}

	/**
	 * To read parameters send 3003 0000 (0x00 0x00 0x0b 0xbb 0x00 0x00 0x00 0x00)
	 * The Luxtronik responds with the command (4 bytes) and the number of
	 * parameters that follow (4 bytes), also formatted big endian.
	 * 
	 * @return
	 */
	@RequestMapping(path = "/calculations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, String> calculations() {
		mLog.debug("/calcuations called!");
		final Map<String, String> result = getParameters(3004, true, 10);
		return result;
	}

	/**
	 * To read parameters send 3003 0000 (0x00 0x00 0x0b 0xbb 0x00 0x00 0x00 0x00)
	 * The Luxtronik responds with the command (4 bytes) and the number of
	 * parameters that follow (4 bytes), also formatted big endian.
	 * 
	 * @return
	 */
	@RequestMapping(path = "/calculation/{parameter}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String calculation(final @PathVariable("parameter") String pParameter) {
		mLog.debug("/calculation/" + pParameter + " called!");
		final Map<String, String> result = getParameters(3004, true, 10);
		return result.get(pParameter);
	}

	/**
	 * Set the heating parameters
	 * 
	 * @param pParameter one of <quote>MODE</quote> or
	 *                   <quote>TemperatureDelta</quote>
	 * @param pValue     on of the <code>OperatingMode</code> enums or a temperature
	 *                   offset.
	 * @return HTTP status
	 */
	@RequestMapping(path = "/heating/{parameter}", method = RequestMethod.PUT, produces = MediaType.TEXT_PLAIN_VALUE)
	public String heating(@PathVariable("parameter") String pParameter, @RequestBody String pValue) {
		mLog.debug("/heating/" + pParameter + " called with value: " + pValue);
		try {
			final HeatingParameter parameter = HeatingParameter.valueOf(pParameter);
			final Class<? extends FormatConverter> convClass = parameter.getFormatConverterClass();
			return setParameter(parameter, parameter.getIntegerValue(), convClass, pValue);
		} catch (IllegalArgumentException iae) {
			throw new InvalidParameterException(pParameter, HeatingParameter.class);
		}
	}

	/**
	 * Set the hotwater parameters
	 * 
	 * @param pParameter one of <quote>MODE</quote> or
	 *                   <quote>TemperatureDelta</quote>
	 * @param pValue     on of the <code>OperatingMode</code> enums or a temperature
	 *                   offset.
	 * @return HTTP status
	 */
	@RequestMapping(path = "/hotwater/{parameter}", method = RequestMethod.PUT, produces = MediaType.TEXT_PLAIN_VALUE)
	public String hotwater(@PathVariable("parameter") String pParameter, @RequestBody String pValue) {
		mLog.debug("/hotwater/" + pParameter + " called with value: " + pValue);
		try {
			final DomesicHotWaterParameter parameter = DomesicHotWaterParameter.valueOf(pParameter);
			final Class<? extends FormatConverter> convClass = parameter.getFormatConverterClass();
			return setParameter(parameter, parameter.getIntegerValue(), convClass, pValue);
		} catch (IllegalArgumentException iae) {
			throw new InvalidParameterException(pParameter, DomesicHotWaterParameter.class);
		}
	}

	/**
	 * Common code for setting heat pump operating mode or temperature delta
	 * parameters. synchronized as there is only one connection to the heatpump in
	 * this version!
	 */
	private String setParameter(final Enum<?> pParameter, final Integer pEnumValue,
			final Class<? extends FormatConverter> pConverter, final String pValue) {
		HttpStatus status = HttpStatus.OK;

		mLog.debug("Parameter: " + pParameter.name());
		mLog.debug("Converter class: " + pConverter);
		mLog.debug("value: " + pValue);

		try {
			final FormatConverter converter = mApplicationContext.getBean(pConverter);
			// shouldn't be null
			final Integer value = converter.convertToHeatPumpFormat(pValue);
			mLog.debug("Converted value: " + value);

			synchronized (mHeatPumpSocketWrapper) {
				mHeatPumpSocketWrapper.write(3002, pEnumValue, value);
				mHeatPumpSocketWrapper.read(2);
			}
		} catch (Exception e) {
			mLog.error("setParameter: Exception Writing Parameter", e);
			throw new RuntimeException(e);
		}
		return status.toString();
	}

	/**
	 * Common code for reading parameters and calculations.synchronized as there is
	 * only one connection to the heatpump in this version!
	 * 
	 * @param pCommand    command to send
	 * @param pReadStatus whether or not to expect the extra status information when
	 *                    reading response.
	 * @return data read from the server.
	 */
	private Map<String, String> getParameters(final int pCommand, final boolean pReadStatus, final int pSkip) {
		Map<String, String> result = Collections.emptyMap();
		try {
			synchronized (mHeatPumpSocketWrapper) {
				mHeatPumpSocketWrapper.write(pCommand, 0);
				final ByteBuffer output = mHeatPumpSocketWrapper.read(pReadStatus);
				output.position(pSkip * HeatPumpSocketWrapper.BYTES_PER_INT);
				result = byteBufferToMap(output, pReadStatus);
			}
		} catch (final Exception e) {
			mLog.error("Exception Writing Parameter", e);
			throw new RuntimeException(e);
		}
		return result;
	}

	private Map<String, String> byteBufferToMap(final ByteBuffer pBuffer, boolean pUseCalculations) {
		final Map<String, String> dataMap = new LinkedHashMap<String, String>();
		for (int i = pBuffer.position(); i < pBuffer.limit(); i += HeatPumpSocketWrapper.BYTES_PER_INT) {
			int index = i / HeatPumpSocketWrapper.BYTES_PER_INT;
			String name = Integer.toString(index);
			Class<? extends FormatConverter> convClass = OneToOneConverter.class;

			// Switch between parameters and calculations
			if (pUseCalculations) {
				final Calculations calc = Calculations.getCalculation(index);
				if (calc != null) {
					convClass = calc.getFormatConverterClass();
					name = calc.name();
				}
			} else {
				final Parameters param = Parameters.getParameter(index);
				if (param != null) {
					convClass = param.getFormatConverterClass();
					name = param.name();
				}
			}

			final FormatConverter conv = mApplicationContext.getBean(convClass);
			dataMap.put(name, conv.convertToHumanReadable(pBuffer.getInt()));

		}

		return dataMap;
	}
}
