package github.ijl.luxtronic.format;

import org.springframework.stereotype.Service;

import github.ijl.luxtronic.param.OperatingMode;

@Service
public class OperatingModeConverter implements FormatConverter {
	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
	 */
	@Override
	public String convertToHumanReadable(final Integer pValue) {
		final OperatingMode opmode = OperatingMode.getOperatingMode(pValue);
		return (opmode != null ? opmode.toString() : "Unknown");
	}

}
