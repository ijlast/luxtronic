package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import github.ijl.luxtronic.config.v161.EnumIndex;
import github.ijl.luxtronic.format.FormatConverter;
import github.ijl.luxtronic.format.OneToOneConverter;
import github.ijl.luxtronic.format.OperatingModeConverter;
import github.ijl.luxtronic.format.output.TemperatureConverter;

@AllArgsConstructor
@Getter
public enum TestEnum implements EnumIndex {
	DEFAULT(-1, "Unknown", OneToOneConverter.class),
	ENUM_1(1, "Enum 1", TemperatureConverter.class),
	ENUM_2(2, "Enum 2", TemperatureConverter.class),
	ENUM_3(3, "Enum 3", OperatingModeConverter.class),
	ENUM_4(4, "Enum 4", OperatingModeConverter.class);

	private final Integer integerValue;
	private final String description;
	private final Class<? extends FormatConverter> formatConverterClass;
}
