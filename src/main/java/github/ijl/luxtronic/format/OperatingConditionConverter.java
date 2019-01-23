package github.ijl.luxtronic.format;

import org.springframework.stereotype.Service;

@Service
public class OperatingConditionConverter implements FormatConverter {
	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
	 */
	@Override
	public String convertToHumanReadable(final Integer pValue) {
		switch (pValue) {
		case 0:
			return "0: heating";
		case 1:
			return "1: hot water";
		case 2:
			return "2: swimming pool / photovoltaic";
		case 3:
			return "3: power supply";
		case 4:
			return "4: defrosting";
		case 5:
			return "5: no request";
		case 6:
			return "6: heating ext. Energy source";
		case 7:
			return "7: cooling mode";

		default:
			return Integer.toString(pValue) + ": Unknown";
		}
	}
}
