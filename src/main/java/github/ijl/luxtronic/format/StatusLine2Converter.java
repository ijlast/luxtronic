package github.ijl.luxtronic.format;

import org.springframework.stereotype.Service;

@Service
public class StatusLine2Converter implements FormatConverter {
	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
	 */
	@Override
	public String convertToHumanReadable(final Integer pValue) {
		switch (pValue) {
		case 0:
			return "0: since";
		case 1:
			return "1: in";

		default:
			return Integer.toString(pValue) + ": Unknown";
		}
	}

}
