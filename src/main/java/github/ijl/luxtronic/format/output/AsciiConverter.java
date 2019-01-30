package github.ijl.luxtronic.format.output;

import org.springframework.stereotype.Service;

import github.ijl.luxtronic.format.FormatConverter;

@Service
public class AsciiConverter implements FormatConverter {
	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
	 */
	@Override
	public String convertToHumanReadable(final Integer pValue) {
		String value;
		if (pValue != 0) {
			final char c = (char) pValue.intValue();
			value = String.valueOf(c);
		} else {
			value = "";
		}
		return value;
	}
}
