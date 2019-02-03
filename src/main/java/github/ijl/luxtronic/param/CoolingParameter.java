package github.ijl.luxtronic.param;

import github.ijl.luxtronic.format.FormatConverter;
import github.ijl.luxtronic.format.OneToOneConverter;

public enum CoolingParameter {
	PARAM_COOLING_OPERATION_MODE(108, OneToOneConverter.class), COOLING_RELEASE_TEMP(110, OneToOneConverter.class),
	COOLING_INLET_TEMP(132, OneToOneConverter.class), COOLING_START(850, OneToOneConverter.class),
	COOLING_STOP(851, OneToOneConverter.class);

	private final Integer mValue;
	private final Class<? extends FormatConverter> mFormatConverterClass;

	private CoolingParameter(final Integer pValue, final Class<? extends FormatConverter> pFormatConverterClass) {
		mValue = pValue;
		mFormatConverterClass = pFormatConverterClass;
	}

	public Integer getIntegerValue() {
		return mValue;
	}

	public Class<? extends FormatConverter> getFormatConverterClass() {
		return mFormatConverterClass;
	}
}
