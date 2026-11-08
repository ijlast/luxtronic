package github.ijl.luxtronik2.format.output;

import org.springframework.stereotype.Service;

import github.ijl.luxtronik2.format.FormatConverter;

@Service
public class TemperatureConverter implements FormatConverter {
    /**
     * @see github.ijl.luxtronik2.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
     */
    @Override
    public String convertToHumanReadable(final Integer pValue) {
        final float value = (float) pValue;
        return Float.toString(value / 10F);
    }
}
