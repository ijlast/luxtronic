package github.ijl.luxtronic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import github.ijl.luxtronic.config.ServiceProperties;
import lombok.AllArgsConstructor;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
@AllArgsConstructor
public class TemperatureDeltaRangeException extends RuntimeException {
	private final String mInputDelta;
	private final ServiceProperties mProperties;

	/**
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return "Temperature Offset '" + mInputDelta + "' must be in the range [" + mProperties.getMinTemperatureDelta()
				+ ", " + mProperties.getMaxTemperatureDelta() + "] C";
	}
}
