package github.ijl.haos.mqtt;

import jakarta.annotation.PreDestroy;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Profile("haos")
@RequiredArgsConstructor
public class MqttShutdown {

    private final IMqttClient client;

    @PreDestroy
    public void onShutdown() {
        try {
            if (client.isConnected()) {
                log.info("Disconnecting MQTT client {}", client.getClientId());
                client.disconnect();
            }
        } catch (Exception e) {
            log.error("Error during MQTT client disconnect: {}", e.getMessage(), e);
        }

        try {
            log.info("Closing MQTT client {}", client.getClientId());
            client.close();
        } catch (Exception e) {
            log.error("Error closing MQTT client: {}", e.getMessage(), e);
        }
    }
}
