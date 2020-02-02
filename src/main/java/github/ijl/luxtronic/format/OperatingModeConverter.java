package github.ijl.luxtronic.format;

import org.springframework.stereotype.Service;

import github.ijl.luxtronic.exception.InvalidOperatingModeException;
import github.ijl.luxtronic.param.OperatingMode;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OperatingModeConverter implements FormatConverter {


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
		log.debug("looking for operating mode: " + pValue);
		Integer value;
		try {
			final OperatingMode mode = OperatingMode.valueOf(pValue);
			value = mode.getIntegerValue();
			log.debug("valid operating mode: " + pValue);
		} catch (IllegalArgumentException iae) {
			log.error("invalid operating mode: " + pValue);
			throw new InvalidOperatingModeException(pValue, OperatingMode.class);
		}
		return value;
	}
}
