package github.ijl.luxtronic.format;

import org.springframework.stereotype.Service;

@Service
public class OneToOneConverter implements FormatConverter {
	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
	 */
	@Override
	public String convertToHumanReadable(final Integer pValue) {
		return Integer.toString(pValue);
	}

	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHeatPumpFormat(java.lang.String)
	 */
	@Override
	public Integer convertToHeatPumpFormat(final String pValue) {
		return Integer.valueOf(pValue);
	}
}
