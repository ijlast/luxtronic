package github.ijl.luxtronik2.format.input;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import github.ijl.luxtronik2.config.ServiceProperties;
import github.ijl.luxtronik2.exception.TemperatureDeltaRangeException;
import github.ijl.luxtronik2.format.FormatConverter;

@Service
@AllArgsConstructor
public class TemperatureDeltaConverter implements FormatConverter {
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
            throw new TemperatureDeltaRangeException(pValue, properties);
        }
        if (value < properties.getMinTemperatureDelta() || value > properties.getMaxTemperatureDelta()) {
            throw new TemperatureDeltaRangeException(pValue, properties);
        }
        return (int) (10 * value);
    }
}
