package github.ijl.haos.mqtt;

import jakarta.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Profile("haos")
@RequiredArgsConstructor
public class MqttDiscoveryPublisher {

    private final MqttPublisher publisher;
    private final String mqttBaseTopic;

    @PostConstruct
    public void publishAllDiscovery() {
        log.info("Publishing Home Assistant MQTT discovery entries");

        TemperatureSensor.all().forEach(sensor -> {
            String topic = "%s/sensor/%s/config".formatted(mqttBaseTopic, sensor.objectId());

            String payload = discoveryPayload(sensor);

            publisher.publish(topic, payload, true);
            log.info("Published discovery for {}", sensor.objectId());
        });
    }

    // -------------------------------------------------------------------------
    // Discovery model
    // -------------------------------------------------------------------------

    // -------------------------------------------------------------------------
    // JSON payload builder (clean, readable, no duplication)
    // -------------------------------------------------------------------------

    private String discoveryPayload(TemperatureSensor s) {
        return """
                {
                  "name": "%s",
                  "uniq_id": "%s",
                  "stat_t": "%s",
                  "unit_of_meas": "°C",
                  "dev_cla": "temperature",
                  "state_class": "measurement",
                  "exp_aft": 600,
                  "dev": {
                    "ids": ["luxtronik_heat_pump"],
                    "name": "Luxtronik Heat Pump",
                    "mf": "Luxtronik",
                    "mdl": "v161"
                  }
                }
                """.formatted(s.name(), s.objectId(), s.stateTopic());
    }
}
