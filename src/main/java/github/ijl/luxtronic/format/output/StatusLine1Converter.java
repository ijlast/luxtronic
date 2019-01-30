package github.ijl.luxtronic.format.output;

import org.springframework.stereotype.Service;

import github.ijl.luxtronic.format.FormatConverter;

@Service
public class StatusLine1Converter implements FormatConverter {
	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
	 */
	@Override
	public String convertToHumanReadable(final Integer pValue) {
		switch (pValue) {
		case 0:
			return "HP is running";
		case 1:
			return "HP is off";
		case 2:
			return "HP is coming";
		case 3:
			return "Error: mem space 0";
		case 4:
			return "Defrost";
		case 5:
			return "Waiting for LIN";
		case 6:
			return "Comp. heats up";
		case 7:
			return "Pump flow";


		default:
			return Integer.toString(pValue) + ": Unknown";
		}
	}

}
