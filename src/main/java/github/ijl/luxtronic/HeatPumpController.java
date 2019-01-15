package github.ijl.luxtronic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import github.ijl.luxtronic.config.ServiceProperties;
import github.ijl.luxtronic.exception.InvalidOperatingModeException;
import github.ijl.luxtronic.exception.InvalidParameterException;
import github.ijl.luxtronic.exception.TemperatureDeltaRangeException;
import github.ijl.luxtronic.param.DomesicHotWaterParameter;
import github.ijl.luxtronic.param.HeatingParameter;
import github.ijl.luxtronic.param.OperatingMode;

@RestController
@RequestMapping("luxtronic")
public class HeatPumpController extends BaseHeatPumpController {
	private Log mLog = LogFactory.getLog(HeatPumpController.class);

	@Autowired
	private ServiceProperties mProperties;

	@PutMapping("/heating/{parameter}/{value}")
	public HttpStatus heating(@PathVariable("parameter") String pParameter, @PathVariable("value") String pValue) {
		try {
			final HeatingParameter parameter = HeatingParameter.valueOf(pParameter);
			return setParameter(HeatingParameter.Mode, parameter, parameter.getIntegerValue(), pValue);
		} catch (IllegalArgumentException iae) {
			throw new InvalidParameterException(pParameter, HeatingParameter.class);
		}
	}

	@PutMapping("/hotwater/{parameter}/{value}")
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
	 * parameters.
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
		mLog.info("Parameter: " + pParameter.name());
		mLog.info("value: " + value);
		try {
			connect();
			write(3002, pEnumValue, value);
			read3002();
		} catch (Exception e) {
			mLog.error("Exception Writing Parameter", e);
			throw new RuntimeException(e);
		}
		return status;

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
}
