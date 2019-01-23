package github.ijl.luxtronic.format;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class BooleanConverter implements FormatConverter {
	private Log mLog = LogFactory.getLog(BooleanConverter.class);

	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
	 */
	@Override
	public String convertToHumanReadable(final Integer pValue) {
		if (pValue > 1) {
			mLog.warn("Converting value >1 to boolean \"false\"");
		}
		return Boolean.toString(pValue.equals(1));
	}

}
