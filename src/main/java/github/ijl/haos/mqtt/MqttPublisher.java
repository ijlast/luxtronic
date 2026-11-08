package github.ijl.haos.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Profile("haos")
@RequiredArgsConstructor
public class MqttPublisher {

    private final IMqttClient client;

    public void publish(String topic, String payload, boolean retain) {
        if (!isConnected()) {
            log.warn("MQTT publish skipped (client disconnected) topic={}", topic);
            return;
        }

        try {
            client.publish(topic, message(payload, retain));
            log.debug("MQTT published topic={} payload={}", topic, payload);
        } catch (Exception e) {
            log.error("MQTT publish failed topic={} error={}", topic, e.getMessage(), e);
        }
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    private boolean isConnected() {
        try {
            return client.isConnected();
        } catch (Exception e) {
            log.error("MQTT connection check failed: {}", e.getMessage(), e);
            return false;
        }
    }

    private MqttMessage message(String payload, boolean retain) {
        MqttMessage msg = new MqttMessage(payload.getBytes());
        msg.setQos(1);
        msg.setRetained(retain);
        return msg;
    }
}
