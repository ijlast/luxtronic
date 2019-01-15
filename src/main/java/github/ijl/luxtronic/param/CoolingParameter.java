package github.ijl.luxtronic.param;

public enum CoolingParameter {
	PARAM_COOLING_OPERATION_MODE(108), COOLING_RELEASE_TEMP(110), COOLING_INLET_TEMP(132), COOLING_START(850),
	COOLING_STOP(851);

	private final Integer mValue;

	private CoolingParameter(final Integer pValue) {
		mValue = pValue;
	}

	public Integer getIntegerValue() {
		return mValue;
	}
}
