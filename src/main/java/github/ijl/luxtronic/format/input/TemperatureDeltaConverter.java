package github.ijl.luxtronic.format.input;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.ijl.luxtronic.config.ServiceProperties;
import github.ijl.luxtronic.exception.TemperatureDeltaRangeException;
import github.ijl.luxtronic.format.FormatConverter;

@Service
public class TemperatureDeltaConverter implements FormatConverter {
    @Autowired
    private ServiceProperties properties;

    /**
     * @see github.ijl.luxtronic.format.FormatConverter#convertToHeatPumpFormat(java.lang.String)
     */
    @Override
    public Integer convertToHeatPumpFormat(final String pValue) {
        float value;
        try {
            value = Float.parseFloat(pValue);
        } catch (NumberFormatException e) {
            throw new TemperatureDeltaRangeException(pValue, properties);
        }
        if (value < properties.getMinTemperatureDelta() || value > properties.getMaxTemperatureDelta()) {
            throw new TemperatureDeltaRangeException(pValue, properties);
        }
        return (int) (10 * value);
    }
}
