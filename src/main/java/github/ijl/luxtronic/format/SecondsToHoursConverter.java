package github.ijl.luxtronic.format;

import org.springframework.stereotype.Service;

@Service
public class SecondsToHoursConverter implements FormatConverter {
	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
	 */
	@Override
	public String convertToHumanReadable(final Integer pValue) {
		float fvalue = (float) pValue;
		return Float.toString(fvalue /3600F);
	}

}
