package github.ijl.luxtronic.param;

public enum DomesicHotWaterParameter {
	TemperatureDelta(2), Mode(4);

	private final Integer mValue;

	private DomesicHotWaterParameter(final Integer pValue) {
		mValue = pValue;
	}

	public Integer getIntegerValue() {
		return mValue;
	}
}
