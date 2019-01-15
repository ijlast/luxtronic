package github.ijl.luxtronic.param;

public enum OperatingMode {
	AUTOMATIC(0), AUXILIARY_HEATER(1), PARTY(2), HOLIDAY(3), OFF(4);

	private final Integer mValue;

	private OperatingMode(final Integer pValue) {
		mValue = pValue;
	}

	public Integer getIntegerValue() {
		return mValue;
	}
}
