package github.ijl.luxtronic.format;

import org.springframework.stereotype.Service;

@Service
public class ShutdownCodeConverter implements FormatConverter {
	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
	 */
	@Override
	public String convertToHumanReadable(final Integer pValue) {
		switch (pValue) {
		case 1:
			return "1: heat pump fault";
		case 2:
			return "2: system fault";
		case 3:
			return "3: operating mode second heat generator";
		case 4:
			return "4: power supply lock";
		case 5:
			return "5: running defrost (only LW devices)";
		case 6:
			return "6: temperature maximum operating limit";
		case 7:
			return "7: temperature minimum operating limit (reversible with LWD possibly shutdown due to frost protection during cooling operation - evaporation temperature too high) long below 0C)";
		case 8:
			return "8: Lower operating limit";
		case 9:
			return "9: No requirement";

		default:
			return Integer.toString(pValue) + ": Unknown";
		}
	}

}
