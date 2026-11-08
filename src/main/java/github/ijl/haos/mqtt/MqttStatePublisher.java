package github.ijl.haos.mqtt;

import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Profile("haos")
@RequiredArgsConstructor
public class MqttStatePublisher {

    private final MqttPublisher publisher;

    public void publish(Map<String, String> calculations) {
        if (calculations == null) {
            log.warn("Skipping MQTT state publish: calculations are null");
            return;
        }

        TemperatureSensor.all().forEach(sensor -> {
            String value = calculations.get(sensor.calculation());
            if (value == null) {
                log.debug("Sensor {} has no calculation value, skipping", sensor.objectId());
                return;
            }

            publisher.publish(sensor.stateTopic(), value, false);

            log.debug("Published {} = {}", sensor.objectId(), value);
        });
    }
}
