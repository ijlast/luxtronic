package github.ijl.luxtronic.format.output;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import github.ijl.luxtronic.format.FormatConverter;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BooleanConverter implements FormatConverter {

	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
	 */
	@Override
	public String convertToHumanReadable(final Integer pValue) {
		return getFunction().apply(pValue);
	}

	public Function<Integer, String> getFunction() {
		return (input) -> {
			if (input > 1) {
				log.warn("Converting value >1 to boolean \"false\"");
			}
			return Boolean.toString(input.equals(1));
		};
	}
}
