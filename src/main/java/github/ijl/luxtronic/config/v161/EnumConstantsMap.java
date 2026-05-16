package github.ijl.luxtronic.config.v161;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class EnumConstantsMap {

	public static <T extends EnumIndex> Map<Integer, T> buildFrom(Class<T> enumClass) {
		Objects.requireNonNull(enumClass);
		return Optional.ofNullable(enumClass.getEnumConstants()).map(Arrays::stream).orElseGet(Stream::empty)
				.collect(Collectors.toMap(EnumIndex::getIntegerValue, enumItem -> enumItem));
	}
}
