package github.ijl.luxtronic.format;

public interface FormatConverter {
	/**
	 * Converts the value from the heat pump into a more recognizable form.
	 * 
	 * @param pInteger the heat pumps representation
	 */
	default String convertToHumanReadable(final Integer pValue) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Converts from a value at the REST service level to something the heatpum will
	 * recognize.
	 * 
	 * @param pValue
	 * @return
	 */
	default Integer convertToHeatPumpFormat(final String pValue) {
		throw new UnsupportedOperationException();
	}
}
