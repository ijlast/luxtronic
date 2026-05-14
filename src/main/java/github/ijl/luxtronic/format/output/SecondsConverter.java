package github.ijl.luxtronic.format.output;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import github.ijl.luxtronic.format.FormatConverter;

@Service
public class SecondsConverter implements FormatConverter {
	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
	 */
	@Override
	public String convertToHumanReadable(final Integer pValue) {
		return getFunction().apply(pValue);
	}

	public Function<Integer, String> getFunction() {
		return (input) -> {
			int seconds = input % 60;
			int minutes = (input / 60) % 60;
			int hours = (input / 3600) % 24;
			int days = (input / 86400);

			final StringBuilder sb = new StringBuilder();
			if (days > 0) {
				sb.append(days);
				sb.append("d ");
			}
			if (hours > 0 || days > 0) {
				sb.append(hours);
				sb.append("h ");
			}
			if (minutes > 0 || hours > 0 || days > 0) {
				sb.append(minutes);
				sb.append("m ");
			}
			sb.append(seconds);
			sb.append('s');

			return sb.toString();
		};
	}
}
