package github.ijl.luxtronic.param;

public enum HeatingParameter {
	TemperatureDelta(1), Mode(3);
	private final Integer mValue;

	private HeatingParameter(final Integer pValue) {
		mValue = pValue;
	}

	public Integer getIntegerValue() {
		return mValue;
	}
}
