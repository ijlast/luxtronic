package github.ijl.luxtronic.format;

import org.springframework.stereotype.Service;

@Service
public class SecondsConverter implements FormatConverter {
	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
	 */
	@Override
	public String convertToHumanReadable(final Integer pValue) {
		int seconds = pValue % 60;
		int minutes = (pValue / 60) % 60;
		int hours = (pValue / 3600) % 24;
		int days = (pValue / 86400);

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
	}

}
