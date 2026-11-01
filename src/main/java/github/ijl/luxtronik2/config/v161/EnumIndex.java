package github.ijl.luxtronik2.config.v161;

/**
 * Exposes the integer protocol address used to look up a calculation or
 * parameter enum by its Luxtronic index.
 */
@FunctionalInterface
public interface EnumIndex {
    String SOFTWARE_VERSION = "software version";
    Integer getIntegerValue();
}
