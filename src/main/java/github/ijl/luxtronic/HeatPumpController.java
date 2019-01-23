package github.ijl.luxtronic;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import github.ijl.luxtronic.config.ServiceProperties;
import github.ijl.luxtronic.config.v161.Calculations;
import github.ijl.luxtronic.exception.InvalidOperatingModeException;
import github.ijl.luxtronic.exception.InvalidParameterException;
import github.ijl.luxtronic.exception.TemperatureDeltaRangeException;
import github.ijl.luxtronic.format.FormatConverter;
import github.ijl.luxtronic.format.OneToOneConverter;
import github.ijl.luxtronic.param.DomesicHotWaterParameter;
import github.ijl.luxtronic.param.HeatingParameter;
import github.ijl.luxtronic.param.OperatingMode;

@RestController
@RequestMapping("luxtronic")
public class HeatPumpController {
	private Log mLog = LogFactory.getLog(HeatPumpController.class);

	@Autowired
	private ApplicationContext mApplicationContext;
	@Autowired
	private HeatPumpSocketWrapper mHeatPumpSocketWrapper;
	@Autowired
	private ServiceProperties mProperties;

	/**
	 * To read parameters send 3003 0000 (0x00 0x00 0x0b 0xbb 0x00 0x00 0x00 0x00)
	 * The Luxtronik responds with the command (4 bytes) and the number of
	 * parameters that follow (4 bytes), also formatted big endian.
	 * 
	 * @return
	 */
	@RequestMapping(path = "/parameters", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Map<String, String> parameters() {
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
	@RequestMapping(path = "/parameter/{parameter}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String parameter(final @PathVariable("parameter") String pParameter) {
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
	@RequestMapping(path = "/calculations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Map<String, String> calculations() {
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
	@RequestMapping(path = "/calculation/{parameter}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody String calculation(final @PathVariable("parameter") String pParameter) {
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
	@RequestMapping(path = "/heating/{parameter}/{value}", method = RequestMethod.PUT, produces = MediaType.TEXT_PLAIN_VALUE)
	public HttpStatus heating(@PathVariable("parameter") String pParameter, @PathVariable("value") String pValue) {
		try {
			final HeatingParameter parameter = HeatingParameter.valueOf(pParameter);
			return setParameter(HeatingParameter.Mode, parameter, parameter.getIntegerValue(), pValue);
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
	@RequestMapping(path = "/hotwater/{parameter}/{value}", method = RequestMethod.PUT, produces = MediaType.TEXT_PLAIN_VALUE)
	public HttpStatus hotwater(@PathVariable("parameter") String pParameter, @PathVariable("value") String pValue) {
		try {
			final DomesicHotWaterParameter parameter = DomesicHotWaterParameter.valueOf(pParameter);
			return setParameter(DomesicHotWaterParameter.Mode, parameter, parameter.getIntegerValue(), pValue);
		} catch (IllegalArgumentException iae) {
			throw new InvalidParameterException(pParameter, DomesicHotWaterParameter.class);
		}
	}

	/**
	 * Common code for setting heat pump operating mode or temperature delta
	 * parameters. synchronized as there is only one connection to the heatpump in
	 * this version!
	 */
	private HttpStatus setParameter(final Enum<?> pMode, final Enum<?> pParameter, final Integer pEnumValue,
			final String pValue) {
		HttpStatus status = HttpStatus.OK;
		Integer value;

		// There are only two options for parameter
		// 1) Set the operating mode
		if (pMode.equals(pParameter)) {
			try {
				final OperatingMode mode = OperatingMode.valueOf(pValue);
				value = mode.getIntegerValue();
			} catch (IllegalArgumentException iae) {
				throw new InvalidOperatingModeException(pValue, OperatingMode.class);
			}
		}
		// 2) Set the temperature delta
		else {
			value = calcTemperatureDelta(pValue);
		}

		// Send the command to the heat pump controller.
		mLog.debug("Parameter: " + pParameter.name());
		mLog.debug("value: " + value);
		try {
			synchronized (mHeatPumpSocketWrapper) {
				mHeatPumpSocketWrapper.write(3002, pEnumValue, value);
				mHeatPumpSocketWrapper.read(2);
			}
		} catch (Exception e) {
			mLog.error("Exception Writing Parameter", e);
			throw new RuntimeException(e);
		}
		return status;
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

	/**
	 * Converts the input value to Integer value for the call to the heatpump.
	 * 
	 * @param pValue
	 * @return
	 * @throws TemperatureDeltaRangeException
	 */
	private Integer calcTemperatureDelta(final String pValue) throws TemperatureDeltaRangeException {
		float tempValue;
		try {
			tempValue = Float.valueOf(pValue);
		} catch (NumberFormatException e) {
			throw new TemperatureDeltaRangeException(pValue, mProperties);
		}
		if (tempValue < mProperties.getMinTemperatureDelta() || tempValue > mProperties.getMaxTemperatureDelta()) {
			throw new TemperatureDeltaRangeException(pValue, mProperties);
		}
		return (int) (10 * tempValue);
	}

	private Map<String, String> byteBufferToMap(final ByteBuffer pBuffer, boolean pUseCalculations) {
		final Map<String, String> dataMap = new LinkedHashMap<String, String>();
		for (int i = pBuffer.position(); i < pBuffer.limit(); i += HeatPumpSocketWrapper.BYTES_PER_INT) {
			int index = i / HeatPumpSocketWrapper.BYTES_PER_INT;
			String name = Integer.toString(index);
			FormatConverter conv = mApplicationContext.getBean(OneToOneConverter.class);
			
			if (pUseCalculations) {
				final Calculations calc = Calculations.getCalculation(index);
				if (calc != null) {
					conv = mApplicationContext.getBean(calc.getFormatConverterClass());
					name = calc.name();
				}
			}
			
			dataMap.put(name, conv.convertToHumanReadable(pBuffer.getInt()));

		}

		return dataMap;
	}
}
