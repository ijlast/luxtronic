package github.ijl.luxtronik2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class ParameterRuntimeException extends RuntimeException {
    public ParameterRuntimeException(final String message, final Exception e) {
        super(message, e);
    }

}
