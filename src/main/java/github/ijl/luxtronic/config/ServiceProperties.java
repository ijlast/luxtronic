package github.ijl.luxtronic.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Setter;
import lombok.Getter;
import lombok.NonNull;

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
