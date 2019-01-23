package github.ijl.luxtronic.format;

import org.springframework.stereotype.Service;

@Service
public class StatusLine3Converter implements FormatConverter {
	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
	 */
	@Override
	public String convertToHumanReadable(final Integer pValue) {
		switch (pValue) {
		case 0:
			return "0: heating mode";
		case 1:
			return "1: no request";
		case 2:
			return "2: mains switch-on delay";
		case 3:
			return "3: switching cycle lock";
		case 4:
			return "4: blocking time";
		case 5:
			return "5: domestic hot water";
		case 6:
			return "6: info heating program";
		case 7:
			return "7: defrost";
		case 8:
			return "8: pump flow";
		case 9:
			return "9: thermal disinfection";
		case 10:
			return "10: cooling mode";
		case 12:
			return "12: swimming pool / photovoltaic ";
		case 13:
			return "13: heating ext. Energy source";
		case 14:
			return "14: hot water ext. Energy source";
		case 16:
			return "16: flow monitoring";
		case 17:
			return "17:  second heat generator 1 operation ";


		default:
			return Integer.toString(pValue) + ": Unknown";
		}
	}
}
