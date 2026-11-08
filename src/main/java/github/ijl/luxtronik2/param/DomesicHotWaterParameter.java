package github.ijl.luxtronik2.param;

import lombok.AllArgsConstructor;
import lombok.Getter;

import github.ijl.luxtronik2.format.FormatConverter;
import github.ijl.luxtronik2.format.OperatingModeConverter;
import github.ijl.luxtronik2.format.input.DHWTargetTemperatureConverter;

@AllArgsConstructor
@Getter
public enum DomesicHotWaterParameter {
    TargetTemperature(2, DHWTargetTemperatureConverter.class),
    Mode(4, OperatingModeConverter.class);

    private final Integer integerValue;
    private final Class<? extends FormatConverter> formatConverterClass;
}
