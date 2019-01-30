package github.ijl.luxtronic.format.output;

import org.springframework.stereotype.Service;

import github.ijl.luxtronic.format.FormatConverter;

@Service
public class IPConverter implements FormatConverter {
	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
	 */
	@Override
	public String convertToHumanReadable(final Integer pValue) {
		int bytes0 = pValue & 0xFF;
		int bytes1 = (pValue >> 8) & 0xFF;
		int bytes2 = (pValue >> 16) & 0xFF;
		int bytes3 = (pValue >> 24) & 0xFF;

		return Integer.toString(bytes3) + '.' + Integer.toString(bytes2) + '.' + Integer.toString(bytes1) + '.'
				+ Integer.toString(bytes0);
	}
}
