package github.ijl.luxtronic.format;

public interface FormatConverter {
	/**
	 * Converts the value from the heat pump into a more recogizable form.
	 */
	String convertToHumanReadable(final Integer pValue);
}
