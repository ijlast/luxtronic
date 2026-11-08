package github.ijl.luxtronik2.format.output;

import org.springframework.stereotype.Service;

import github.ijl.luxtronik2.format.FormatConverter;

@Service
public class SecondsToHoursConverter implements FormatConverter {
    /**
     * @see github.ijl.luxtronik2.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
     */
    @Override
    public String convertToHumanReadable(final Integer pValue) {
        float fvalue = pValue;
        return Float.toString(fvalue / 3600F);
    }
}
