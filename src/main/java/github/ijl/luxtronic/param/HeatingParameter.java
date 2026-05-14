package github.ijl.luxtronic.param;

import github.ijl.luxtronic.format.FormatConverter;
import github.ijl.luxtronic.format.OperatingModeConverter;
import github.ijl.luxtronic.format.input.TemperatureDeltaConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum HeatingParameter {
	TemperatureDelta(1, TemperatureDeltaConverter.class), Mode(3, OperatingModeConverter.class);
	private final Integer integerValue;
	private final Class<? extends FormatConverter> formatConverterClass;
}
