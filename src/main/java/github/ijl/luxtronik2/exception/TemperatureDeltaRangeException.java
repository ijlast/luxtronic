package github.ijl.luxtronik2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;

import github.ijl.luxtronik2.config.ServiceProperties;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
@AllArgsConstructor
public class TemperatureDeltaRangeException extends RuntimeException {
    private final String inputDelta;
    private final ServiceProperties properties;

    /**
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        return "Temperature Offset '" + inputDelta + "' must be in the range [" + properties.getMinTemperatureDelta() + ", " + properties.getMaxTemperatureDelta() + "] C";
    }
}
