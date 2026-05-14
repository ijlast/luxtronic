package github.ijl.luxtronic.param;

import github.ijl.luxtronic.format.FormatConverter;
import github.ijl.luxtronic.format.OperatingModeConverter;
import github.ijl.luxtronic.format.input.DHWTargetTemperatureConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DomesicHotWaterParameter {
	TargetTemperature(2, DHWTargetTemperatureConverter.class), Mode(4, OperatingModeConverter.class);

	private final Integer integerValue;
	private final Class<? extends FormatConverter> formatConverterClass;
}
