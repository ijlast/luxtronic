package github.ijl.luxtronic.format;

import org.springframework.stereotype.Service;

@Service
public class TemperatureConverter implements FormatConverter {
	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
	 */
	@Override
	public String convertToHumanReadable(final Integer pValue) {
		final float value = (float) pValue;
		return Float.toString(value / 10F);
	}

}
