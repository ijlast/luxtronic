package github.ijl.luxtronik2.param;

import lombok.AllArgsConstructor;
import lombok.Getter;

import github.ijl.luxtronik2.format.FormatConverter;
import github.ijl.luxtronik2.format.OperatingModeConverter;
import github.ijl.luxtronik2.format.input.TemperatureDeltaConverter;

@AllArgsConstructor
@Getter
public enum HeatingParameter {
    TemperatureDelta(1, TemperatureDeltaConverter.class),
    Mode(3, OperatingModeConverter.class);
    private final Integer integerValue;
    private final Class<? extends FormatConverter> formatConverterClass;
}
