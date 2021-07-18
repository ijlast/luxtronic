package github.ijl.luxtronic.format.output;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import github.ijl.luxtronic.format.FormatConverter;

@Service
public class AbstractMapBasedConverter implements FormatConverter {
	private final Map<Integer, String> humanReadableValues;
	
	/**
	 * 
	 * @param pHumanReadableValues object array consisting of integer key and string value.
	 */
	protected  AbstractMapBasedConverter(final Object[][] pHumanReadableValues) {
		humanReadableValues = Stream
				.of(pHumanReadableValues)
				.collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]));
	}
	
	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
	 */
	@Override
	public String convertToHumanReadable(final Integer pValue) {
		return getFunction().apply(pValue);
	}

	public Function<Integer, String> getFunction() {
		return (input) -> {
			if (humanReadableValues.containsKey(input)) {
				return humanReadableValues.get(input);
			} else {
				return Integer.toString(input) + ": Unknown";
			}
		};
	}
}
