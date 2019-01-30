package github.ijl.luxtronic.config.v161;

import github.ijl.luxtronic.format.FormatConverter;
import github.ijl.luxtronic.format.OneToOneConverter;
import github.ijl.luxtronic.format.OperatingModeConverter;
import github.ijl.luxtronic.format.output.TemperatureConverter;

public enum Parameters {
	// TODO
	DEFAULT(-1, "Unknown", OneToOneConverter.class), ID_Einst_WK_akt(1, "Unknown", TemperatureConverter.class),
	ID_Einst_BWS_akt(2, "Unknown", TemperatureConverter.class),
	ID_Ba_Hz_akt(3, "Unknown", OperatingModeConverter.class), ID_Ba_Bw_akt(4, "Unknown", OperatingModeConverter.class);

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

	public static Parameters getParameter(final int pIndex) {
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
