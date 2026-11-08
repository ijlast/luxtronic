package github.ijl.haos.service;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import github.ijl.haos.mqtt.MqttStatePublisher;
import github.ijl.luxtronik2.HeatPumpController;

@Slf4j
@Service
@Profile("haos")
@RequiredArgsConstructor
public class PollingService {

    private final HeatPumpController heatPumpController;
    private final MqttStatePublisher statePublisher;

    @Scheduled(fixedRateString = "${luxtronic.poll-interval-ms:60000}")
    public void poll() {
        try {
            log.debug("Polling Luxtronic heat pump");

            statePublisher.publish(heatPumpController.calculations());

        } catch (Exception e) {
            log.error("Error during Luxtronic polling or MQTT publishing: {}", e.getMessage(), e);
        }
    }
}
