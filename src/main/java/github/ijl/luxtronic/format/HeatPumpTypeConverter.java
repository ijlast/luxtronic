package github.ijl.luxtronic.format;

import org.springframework.stereotype.Service;

@Service
public class HeatPumpTypeConverter implements FormatConverter {

	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
	 */
	@Override
	public String convertToHumanReadable(final Integer pValue) {
		switch (pValue) {
		case 0:
			return "ERC";
		case 1:
			return "SW1";
		case 2:
			return "SW2";
		case 3:
			return "WW1";
		case 4:
			return "WW2";
		case 5:
			return "L1I";
		case 6:
			return "L2I";
		case 7:
			return "L1A";
		case 8:
			return "L2A";
		case 9:
			return "KSW";
		case 10:
			return "KLW";
		case 11:
			return "SWC";
		case 12:
			return "LWC";
		case 13:
			return "L2G";
		case 14:
			return "TC";
		case 15:
			return "L1I407";
		case 16:
			return "L2I407";
		case 17:
			return "L1A407";
		case 18:
			return "L2A407";
		case 19:
			return "L2G407";
		case 20:
			return "LWC407";
		case 21:
			return "L1AREV";
		case 22:
			return "L2AREV";
		case 23:
			return "WWC1";
		case 24:
			return "WWC2";
		case 25:
			return "L2G404";
		case 26:
			return "WZW";
		case 27:
			return "L1S";
		case 28:
			return "L1H";
		case 29:
			return "L2H";
		case 30:
			return "WZWD";
		case 31:
			return "ERC";
		case 40:
			return "WWB_20";
		case 41:
			return "LD5";
		case 42:
			return "LD7";
		case 43:
			return "SW 37_45";
		case 44:
			return "SW 58_69";
		case 45:
			return "SW 29_56";
		case 46:
			return "LD5 (230V)";
		case 47:
			return "LD7 (230V)";
		case 48:
			return "LD9";
		case 49:
			return "LD5 REV";
		case 50:
			return "LD7 REV";
		case 51:
			return "LD5 REV 230V";
		case 52:
			return "LD7 REV 230V";
		case 53:
			return "LD9 REV 230V";
		case 54:
			return "SW 291";
		case 55:
			return "LW SEC";
		case 56:
			return "HMD 2";
		case 57:
			return "MSW 4";
		case 58:
			return "MSW 6";
		case 59:
			return "MSW 8";
		case 60:
			return "MSW 10";
		case 61:
			return "MSW 12";
		case 62:
			return "MSW 14";
		case 63:
			return "MSW 17";
		case 64:
			return "MSW 19";
		case 65:
			return "MSW 23";
		case 66:
			return "MSW 26";
		case 67:
			return "MSW 30";
		case 68:
			return "MSW 4S";
		case 69:
			return "MSW 6S";
		case 70:
			return "MSW 8S";
		case 71:
			return "MSW 10S";
		case 72:
			return "MSW 13S";
		case 73:
			return "MSW 16S";
		case 74:
			return "MSW2-6S";
		case 75:
			return "MSW4-16";
		default:
			return "Unknown";
		}
	}

}
