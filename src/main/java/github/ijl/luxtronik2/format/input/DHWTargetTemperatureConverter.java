package github.ijl.luxtronik2.format.input;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import github.ijl.luxtronik2.config.ServiceProperties;
import github.ijl.luxtronik2.exception.DHWTemperatureRangeException;
import github.ijl.luxtronik2.format.FormatConverter;

@Service
@AllArgsConstructor
public class DHWTargetTemperatureConverter implements FormatConverter {
    private final ServiceProperties properties;

    /**
     * @see github.ijl.luxtronik2.format.FormatConverter#convertToHeatPumpFormat(java.lang.String)
     */
    @Override
    public Integer convertToHeatPumpFormat(final String pValue) {
        float value;
        try {
            value = Float.parseFloat(pValue);
        } catch (NumberFormatException _) {
            throw new DHWTemperatureRangeException(pValue, properties);
        }
        if (value < properties.getMinDHWTargetTemperature() || value > properties.getMaxDHWTargetTemperature()) {
            throw new DHWTemperatureRangeException(pValue, properties);
        }
        return (int) (10 * value);
    }
}
