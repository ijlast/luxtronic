package github.ijl.luxtronic.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@ConfigurationProperties(prefix = "heatpump")
@Component
@Getter
@Setter
@NonNull
public class ServiceProperties {
    private String ip;
    private String port;

    private Float minTemperatureDelta;
    private Float maxTemperatureDelta;

    private Float minDHWTargetTemperature;
    private Float maxDHWTargetTemperature;
}
