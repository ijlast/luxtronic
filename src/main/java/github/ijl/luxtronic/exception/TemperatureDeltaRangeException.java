package github.ijl.luxtronic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import github.ijl.luxtronic.config.ServiceProperties;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TemperatureDeltaRangeException extends RuntimeException {
	final String mInputDelta;
	private ServiceProperties mProperties;

	public TemperatureDeltaRangeException(final String pInputDelta, final ServiceProperties pServiceProperties) {
		mInputDelta = pInputDelta;
		mProperties = pServiceProperties;
	}

	/**
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return "Temperature Offset '" + mInputDelta + "' must be in the range [" + mProperties.getMinTemperatureDelta()
				+ ", " + mProperties.getMaxTemperatureDelta() + "] C";
	}
}
