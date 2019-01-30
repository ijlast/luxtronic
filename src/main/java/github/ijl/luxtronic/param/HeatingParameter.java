package github.ijl.luxtronic.param;

import github.ijl.luxtronic.format.FormatConverter;
import github.ijl.luxtronic.format.OperatingModeConverter;
import github.ijl.luxtronic.format.input.TemperatureDeltaConverter;

public enum HeatingParameter {
	TemperatureDelta(1, TemperatureDeltaConverter.class), Mode(3, OperatingModeConverter.class);
	private final Integer mValue;

	private final Class<? extends FormatConverter> mFormatConverterClass;

	private HeatingParameter(final Integer pValue, final Class<? extends FormatConverter> pFormatConverterClass) {
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
