package github.ijl.luxtronic.exception;

import java.util.stream.Stream;

import lombok.AllArgsConstructor;

@SuppressWarnings("serial")
@AllArgsConstructor
public abstract class AbstractInvalidEnumValueException extends RuntimeException {
	private Class<? extends Enum<?>> mEnum;

	/**
	 * Get the possible values from an Enum as a String.
	 * 
	 * @return comma separated values.
	 */
	public String getEnumValues() {
		final StringBuilder sb = new StringBuilder();
		final Stream<?> valueStream = Stream.of(mEnum.getEnumConstants());
		valueStream.forEachOrdered(x -> sb.append(x).append(','));
		if (sb.length() > 0) {
			sb.setLength(sb.length() - 1);
		}
		return sb.toString();
	}
}
