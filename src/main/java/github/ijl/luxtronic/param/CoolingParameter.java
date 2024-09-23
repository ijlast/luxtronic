package github.ijl.luxtronic.param;

import github.ijl.luxtronic.format.FormatConverter;
import github.ijl.luxtronic.format.OneToOneConverter;
<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
=======

>>>>>>> 7665596bc8ef56f205975ca5f875c95003521aa6
public enum CoolingParameter {
	PARAM_COOLING_OPERATION_MODE(108, OneToOneConverter.class), COOLING_RELEASE_TEMP(110, OneToOneConverter.class),
	COOLING_INLET_TEMP(132, OneToOneConverter.class), COOLING_START(850, OneToOneConverter.class),
	COOLING_STOP(851, OneToOneConverter.class);

<<<<<<< HEAD
	private final Integer integerValue;
	private final Class<? extends FormatConverter> formatConverterClass;
=======
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
>>>>>>> 7665596bc8ef56f205975ca5f875c95003521aa6
}
