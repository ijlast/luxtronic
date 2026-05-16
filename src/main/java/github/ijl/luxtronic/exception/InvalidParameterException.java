package github.ijl.luxtronic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidParameterException extends AbstractInvalidEnumValueException {
    final String currentParameter;

    public InvalidParameterException(final String pCurrentParameter, final Class<? extends Enum<?>> pEnum) {
        super(pEnum);
        currentParameter = pCurrentParameter;
    }

    /**
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        return "Parameter '" + currentParameter + "' must be one of : " + getEnumValues();
    }
}
