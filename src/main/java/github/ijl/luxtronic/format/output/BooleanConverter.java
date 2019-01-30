package github.ijl.luxtronic.format.output;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import github.ijl.luxtronic.format.FormatConverter;

@Service
public class BooleanConverter implements FormatConverter {
	private Logger mLog = LoggerFactory.getLogger(BooleanConverter.class);

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
