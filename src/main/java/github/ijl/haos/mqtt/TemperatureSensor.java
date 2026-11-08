package github.ijl.haos.mqtt;

import java.util.List;
import java.util.Locale;

import github.ijl.luxtronik2.config.v161.Calculations;

/**
 * Describes one of the temperature calculations selected for the initial Home
 * Assistant dashboard.
 */
record TemperatureSensor(String objectId, String name, String calculation, String stateTopic) {

    static List<TemperatureSensor> all() {
        return List.of(Calculations.ID_WEB_TEMPERATUR_TVL, Calculations.ID_WEB_TEMPERATUR_TRL, Calculations.ID_WEB_TEMPERATUR_TA, Calculations.ID_WEB_TEMPERATUR_TBW,
                Calculations.ID_WEB_EINST_BWS_AKT).stream().map(TemperatureSensor::fromCalculation).toList();
    }

    private static TemperatureSensor fromCalculation(Calculations calculation) {
        String objectId = calculation.name().toLowerCase(Locale.ROOT);
        String name = calculation.getDescription().replaceFirst("\\.$", "");
        return new TemperatureSensor(objectId, name, calculation.name(), "luxtronik/sensor/" + objectId + "/state");
    }
}
