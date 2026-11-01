package github.ijl.haos.mqtt;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import github.ijl.luxtronik2.config.v161.Calculations;
import github.ijl.luxtronik2.format.output.TemperatureConverter;

/**
 * Describes a Home Assistant sensor derived from a Luxtronic temperature
 * calculation. The list is generated from all calculations using
 * {@link TemperatureConverter}, so adding a temperature mapping also makes it
 * eligible for MQTT discovery and state publishing.
 */
record TemperatureSensor(String objectId, String name, String calculation, String stateTopic) {

    static List<TemperatureSensor> all() {
        return Arrays.stream(Calculations.values()).filter(calculation -> calculation.getFormatConverterClass() == TemperatureConverter.class)
                .map(TemperatureSensor::fromCalculation).toList();
    }

    private static TemperatureSensor fromCalculation(Calculations calculation) {
        String objectId = calculation.name().toLowerCase(Locale.ROOT);
        String name = calculation.getDescription().replaceFirst("\\.$", "");
        return new TemperatureSensor(objectId, name, calculation.name(), "luxtronic/sensor/" + objectId + "/state");
    }
}
