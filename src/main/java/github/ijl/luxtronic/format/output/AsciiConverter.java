package github.ijl.luxtronic.format.output;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import github.ijl.luxtronic.format.FormatConverter;

@Service
public class AsciiConverter implements FormatConverter {
	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
	 */
	@Override
	public String convertToHumanReadable(final Integer pValue) {
		return getFunction().apply(pValue);
	}

	public Function<Integer, String> getFunction() {
		return (input) -> {
			if (input != 0) {
				final char c = (char) input.intValue();
				return String.valueOf(c);
			} else {
				return "";
			}
		};
	}
}
