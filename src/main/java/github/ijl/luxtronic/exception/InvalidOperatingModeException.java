package github.ijl.luxtronic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidOperatingModeException extends AbstractInvalidEnumValueException {
	final String mInputMode;

	public InvalidOperatingModeException(final String pInputParameter, final Class<? extends Enum<?>> pEnum) {
		super(pEnum);
		mInputMode = pInputParameter;
	}

	/*
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return "Mode '" + mInputMode + "' must be one of : " + getEnumValues();
	}
}
