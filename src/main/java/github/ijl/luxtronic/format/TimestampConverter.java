package github.ijl.luxtronic.format;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class TimestampConverter implements FormatConverter {
	private final DateFormat mFormatter = new SimpleDateFormat("dd:MM:yy HH:mm:ss");

	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
	 */
	@Override
	public String convertToHumanReadable(final Integer pValue) {
		final Date ts = new Date();
		long lvalue = (long) pValue;
		ts.setTime(lvalue * 1000L); // pValue is in seconds

		return mFormatter.format(ts);
	}
}
