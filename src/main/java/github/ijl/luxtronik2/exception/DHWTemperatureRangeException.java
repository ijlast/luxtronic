package github.ijl.luxtronik2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;

import github.ijl.luxtronik2.config.ServiceProperties;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
@AllArgsConstructor
public class DHWTemperatureRangeException extends RuntimeException {
    private final String inputTemp;
    private final ServiceProperties properties;

    /**
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        return "Temperature '" + inputTemp + "' must be in the range [" + properties.getMinDHWTargetTemperature() + ", " + properties.getMaxDHWTargetTemperature() + "] C";
    }
}
