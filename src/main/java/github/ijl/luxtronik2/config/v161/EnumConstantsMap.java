package github.ijl.luxtronik2.config.v161;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.experimental.UtilityClass;

/**
 * Builds reverse lookup maps from Luxtronic integer protocol addresses to their
 * calculation or parameter enum constants.
 */
@UtilityClass
public final class EnumConstantsMap {

    /**
     * Builds a map keyed by each enum constant's protocol integer. The integer
     * values must be unique within the enum.
     */
    public static <T extends EnumIndex> Map<Integer, T> buildFrom(Class<T> enumClass) {
        Objects.requireNonNull(enumClass);
        return Optional.ofNullable(enumClass.getEnumConstants()).map(Arrays::stream).orElseGet(Stream::empty)
                .collect(Collectors.toMap(EnumIndex::getIntegerValue, enumItem -> enumItem));
    }
}
