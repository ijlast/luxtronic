package github.ijl.luxtronic.param;

import github.ijl.luxtronic.format.FormatConverter;
import github.ijl.luxtronic.format.OperatingModeConverter;
import github.ijl.luxtronic.format.input.DHWTargetTemperatureConverter;

public enum DomesicHotWaterParameter {
	TargetTemperature(2, DHWTargetTemperatureConverter.class), Mode(4, OperatingModeConverter.class);

	private final Integer mValue;
	private final Class<? extends FormatConverter> mFormatConverterClass;

	private DomesicHotWaterParameter(final Integer pValue,
			final Class<? extends FormatConverter> pFormatConverterClass) {
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
