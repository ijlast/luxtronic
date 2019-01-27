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
			return "heating mode";
		case 1:
			return "no request";
		case 2:
			return "mains switch-on delay";
		case 3:
			return "switching cycle lock";
		case 4:
			return "blocking time";
		case 5:
			return "dom. hot water";
		case 6:
			return "info heating program";
		case 7:
			return "defrost";
		case 8:
			return "pump flow";
		case 9:
			return "thermal disinfection";
		case 10:
			return "cooling mode";
		case 12:
			return "swimming pool / photovoltaic ";
		case 13:
			return "heating ext. Energy source";
		case 14:
			return "hot water ext. Energy source";
		case 16:
			return "flow monitoring";
		case 17:
			return "second heat generator 1 operation ";


		default:
			return Integer.toString(pValue) + ": Unknown";
		}
	}
}
