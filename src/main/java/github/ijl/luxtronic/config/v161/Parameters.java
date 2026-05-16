package github.ijl.luxtronic.config.v161;

import lombok.AllArgsConstructor;
import lombok.Getter;

import github.ijl.luxtronic.format.FormatConverter;
import github.ijl.luxtronic.format.OneToOneConverter;
import github.ijl.luxtronic.format.OperatingModeConverter;
import github.ijl.luxtronic.format.output.TemperatureConverter;

@AllArgsConstructor
@Getter
public enum Parameters implements EnumIndex {
    DEFAULT(-1, "Unknown", OneToOneConverter.class),
    ID_EINST_WK_AKT(1, "Heating Target Temperature Delta", TemperatureConverter.class),
    ID_EINST_BWS_AKT(2, "DHW Target Temperature", TemperatureConverter.class),
    ID_BA_HZ_AKT(3, "Heating Mode", OperatingModeConverter.class),
    ID_BA_BW_AKT(4, "DHW Mode", OperatingModeConverter.class);

    private final Integer integerValue;
    private final String description;
    private final Class<? extends FormatConverter> formatConverterClass;
}
