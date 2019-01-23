package github.ijl.luxtronic.format;

import org.springframework.stereotype.Service;

@Service
public class StatusLine1Converter implements FormatConverter {
	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
	 */
	@Override
	public String convertToHumanReadable(final Integer pValue) {
		switch (pValue) {
		case 0:
			return "0: Heat pump is running";
		case 1:
			return "1: Heat pump is";
		case 2:
			return "2: Heat pump is coming";
		case 3:
			return "3: Error code memory space 0";
		case 4:
			return "4: Defrost";
		case 5:
			return "5: Waiting for LIN connection";
		case 6:
			return "6: Compressor heats up";
		case 7:
			return "7: Pump flow";


		default:
			return Integer.toString(pValue) + ": Unknown";
		}
	}

}
