package github.ijl.luxtronic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidParameterException extends AbstractInvalidEnumValueException {
	final String mCurrentParameter;

	public InvalidParameterException(final String pCurrentParameter, final Class<? extends Enum<?>> pEnum) {
		super(pEnum);
		mCurrentParameter = pCurrentParameter;
	}

	/**
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return "Parameter '" + mCurrentParameter + "' must be one of : " + getEnumValues();
	}
}
