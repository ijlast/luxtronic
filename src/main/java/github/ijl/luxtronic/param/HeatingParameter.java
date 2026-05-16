package github.ijl.luxtronic.param;

import lombok.AllArgsConstructor;
import lombok.Getter;

import github.ijl.luxtronic.format.FormatConverter;
import github.ijl.luxtronic.format.OperatingModeConverter;
import github.ijl.luxtronic.format.input.TemperatureDeltaConverter;

@AllArgsConstructor
@Getter
public enum HeatingParameter {
	TemperatureDelta(1, TemperatureDeltaConverter.class),
	Mode(3, OperatingModeConverter.class);
	private final Integer integerValue;
	private final Class<? extends FormatConverter> formatConverterClass;
}
