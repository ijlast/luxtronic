package github.ijl.luxtronic.config.v161;

import github.ijl.luxtronic.format.FormatConverter;
import github.ijl.luxtronic.format.OneToOneConverter;

public enum Parameters {
	// TODO
	DEFAULT(-1, "Unknown", OneToOneConverter.class);

	private final Integer mIndex;
	private final String mDescription;
	private final Class<? extends FormatConverter> mFormatConverterClass;

	private Parameters(final Integer pIndex, final String pDescription,
			final Class<? extends FormatConverter> pConverterClass) {
		mIndex = pIndex;
		mDescription = pDescription;
		mFormatConverterClass = pConverterClass;
	}

	public Integer getIntegerValue() {
		return mIndex;
	}

	public String getDescription() {
		return mDescription;
	}

	public Class<? extends FormatConverter> getFormatConverterClass() {
		return mFormatConverterClass;
	}

	public static Parameters getCalculation(final int pIndex) {
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
