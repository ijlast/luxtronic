package github.ijl.luxtronic.format.output;

import org.springframework.stereotype.Service;

import github.ijl.luxtronic.format.FormatConverter;

@Service
public class StatusLine2Converter implements FormatConverter {
	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
	 */
	@Override
	public String convertToHumanReadable(final Integer pValue) {
		switch (pValue) {
		case 0:
			return "since";
		case 1:
			return "in";

		default:
			return Integer.toString(pValue) + ": Unknown";
		}
	}

}
