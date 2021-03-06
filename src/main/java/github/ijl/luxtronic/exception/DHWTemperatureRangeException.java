package github.ijl.luxtronic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import github.ijl.luxtronic.config.ServiceProperties;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DHWTemperatureRangeException extends RuntimeException {
	final String mInputTemp;
	private ServiceProperties mProperties;

	public DHWTemperatureRangeException(final String pInputTemperature, final ServiceProperties pServiceProperties) {
		mInputTemp = pInputTemperature;
		mProperties = pServiceProperties;
	}

	/**
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return "Temperature '" + mInputTemp + "' must be in the range [" + mProperties.getMinDHWTargetTemperature()
				+ ", " + mProperties.getMaxDHWTargetTemperature() + "] C";
	}
}
