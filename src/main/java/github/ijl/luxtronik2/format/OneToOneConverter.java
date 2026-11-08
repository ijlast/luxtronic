package github.ijl.luxtronik2.format;

import org.springframework.stereotype.Service;

@Service
public class OneToOneConverter implements FormatConverter {
    /**
     * @see github.ijl.luxtronik2.format.FormatConverter#convertToHumanReadable(java.lang.Integer)
     */
    @Override
    public String convertToHumanReadable(final Integer pValue) {
        return Integer.toString(pValue);
    }

    /**
     * @see github.ijl.luxtronik2.format.FormatConverter#convertToHeatPumpFormat(java.lang.String)
     */
    @Override
    public Integer convertToHeatPumpFormat(final String pValue) {
        return Integer.valueOf(pValue);
    }
}
