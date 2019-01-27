package github.ijl.luxtronic.format;

import org.springframework.stereotype.Service;

@Service
public class SecondsConverter implements FormatConverter {
	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
	 */
	@Override
	public String convertToHumanReadable(final Integer pValue) {
		return Integer.toString(pValue) + " s";
	}

}
