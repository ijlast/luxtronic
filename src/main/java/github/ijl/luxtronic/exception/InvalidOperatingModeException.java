package github.ijl.luxtronic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidOperatingModeException extends AbstractInvalidEnumValueException {
    final String inputMode;

    public InvalidOperatingModeException(final String pInputParameter, final Class<? extends Enum<?>> pEnum) {
        super(pEnum);
        inputMode = pInputParameter;
    }

    /*
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        return "Mode '" + inputMode + "' must be one of : " + getEnumValues();
    }
}
