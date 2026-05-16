package github.ijl.luxtronic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;

import github.ijl.luxtronic.config.ServiceProperties;

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
