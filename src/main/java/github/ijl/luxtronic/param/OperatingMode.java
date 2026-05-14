package github.ijl.luxtronic.param;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OperatingMode {
	AUTOMATIC(0), AUXILIARY_HEATER(1), PARTY(2), HOLIDAY(3), OFF(4);

	private final Integer integerValue;

	public static OperatingMode getOperatingMode(final int pIndex) {
		OperatingMode opmode = null;
		for (final OperatingMode testCalc : OperatingMode.class.getEnumConstants()) {
			if (pIndex == testCalc.getIntegerValue()) {
				opmode = testCalc;
				break;
			}
		}
		return opmode;
	}
}
