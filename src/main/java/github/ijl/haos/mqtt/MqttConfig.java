package github.ijl.haos.mqtt;

import java.util.Optional;
import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.extern.slf4j.Slf4j;

@Profile("haos")
@Slf4j
@Configuration
public class MqttConfig {

    @Bean
    public IMqttClient mqttClient() throws Exception {
        String host = env("MQTT_HOST", "localhost");
        int port = envInt("MQTT_PORT", 1883);
        String clientId = env("MQTT_CLIENT_ID", "luxtronik-" + UUID.randomUUID());

        String uri = "tcp://" + host + ":" + port;
        log.info("Connecting to MQTT broker at {}", uri);

        IMqttClient client = new MqttClient(uri, clientId);
        client.connect(connectOptions());

        log.info("Connected to MQTT broker as {}", clientId);
        return client;
    }

    @Bean
    public String mqttBaseTopic() {
        return env("MQTT_BASE_TOPIC", "homeassistant");
    }

    // -------------------------------------------------------------------------
    // Helper methods (clean, reusable, idiomatic)
    // -------------------------------------------------------------------------

    private MqttConnectOptions connectOptions() {
        MqttConnectOptions opts = new MqttConnectOptions();
        opts.setAutomaticReconnect(true);
        opts.setCleanSession(true);
        opts.setConnectionTimeout(10);

        envOpt("MQTT_USERNAME").ifPresent(opts::setUserName);
        envOpt("MQTT_PASSWORD").ifPresent(p -> opts.setPassword(p.toCharArray()));

        return opts;
    }

    private String env(String key, String defaultValue) {
        String value = System.getenv(key);
        return (value == null || value.isBlank()) ? defaultValue : value;
    }

    private int envInt(String key, int defaultValue) {
        String value = System.getenv(key);
        if (value == null || value.isBlank())
            return defaultValue;
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            log.warn("Invalid integer for {}: '{}', using default {}", key, value, defaultValue);
            return defaultValue;
        }
    }

    private Optional<String> envOpt(String key) {
        String value = System.getenv(key);
        return (value == null || value.isBlank()) ? Optional.empty() : Optional.of(value);
    }
}
