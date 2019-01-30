package github.ijl.luxtronic.format;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import github.ijl.luxtronic.HeatPumpController;
import github.ijl.luxtronic.exception.InvalidOperatingModeException;
import github.ijl.luxtronic.param.OperatingMode;

@Service
public class OperatingModeConverter implements FormatConverter {
	private Logger mLog = LoggerFactory.getLogger(HeatPumpController.class);

	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
	 */
	@Override
	public String convertToHumanReadable(final Integer pValue) {
		final OperatingMode opmode = OperatingMode.getOperatingMode(pValue);
		return (opmode != null ? opmode.toString() : "Unknown");
	}

	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHeatPumpFormat(java.lang.String)
	 */
	@Override
	public Integer convertToHeatPumpFormat(final String pValue) {
		mLog.debug("looking for operating mode: " + pValue);
		Integer value;
		try {
			final OperatingMode mode = OperatingMode.valueOf(pValue);
			value = mode.getIntegerValue();
			mLog.debug("valid operating mode: " + pValue);
		} catch (IllegalArgumentException iae) {
			mLog.error("invalid operating mode: " + pValue);
			throw new InvalidOperatingModeException(pValue, OperatingMode.class);
		}
		return value;
	}
}
