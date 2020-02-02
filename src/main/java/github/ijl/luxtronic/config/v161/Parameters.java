package github.ijl.luxtronic.config.v161;

import github.ijl.luxtronic.format.FormatConverter;
import github.ijl.luxtronic.format.OneToOneConverter;
import github.ijl.luxtronic.format.OperatingModeConverter;
import github.ijl.luxtronic.format.output.TemperatureConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Parameters {
	// TODO
	DEFAULT(-1, "Unknown", OneToOneConverter.class),
	ID_Einst_WK_akt(1, "Heating Target Temperature Delta", TemperatureConverter.class),
	ID_Einst_BWS_akt(2, "DHW Target Temperature", TemperatureConverter.class),
	ID_Ba_Hz_akt(3, "Heating Mode", OperatingModeConverter.class),
	ID_Ba_Bw_akt(4, "DHW Mode", OperatingModeConverter.class);

	private final Integer integerValue;
	private final String description;
	private final Class<? extends FormatConverter> formatConverterClass;

	public static Parameters getParameter(final int pIndex) {
		Parameters calc = null;
		for (final Parameters testCalc : Parameters.class.getEnumConstants()) {
			if (pIndex == testCalc.getIntegerValue()) {
				calc = testCalc;
				break;
			}
		}
		return calc;
	}
}
