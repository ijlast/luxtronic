package github.ijl.luxtronic.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "heatpump")
@Component
public class ServiceProperties {
	private String mIp;
	private String mPort;
	private Float mMinTemperatureDelta;
	private Float mMaxTemperatureDelta;

	public String getIp() {
		return mIp;
	}

	public void setIp(String ip) {
		mIp = ip;
	}

	public String getPort() {
		return mPort;
	}

	public void setPort(String port) {
		mPort = port;
	}

	public Float getMinTemperatureDelta() {
		return mMinTemperatureDelta;
	}

	public void setMinTemperatureDelta(final Float pMinTemperatureDelta) {
		mMinTemperatureDelta = pMinTemperatureDelta;
	}

	public Float getMaxTemperatureDelta() {
		return mMaxTemperatureDelta;
	}

	public void setMaxTemperatureDelta(final Float pMaxTemperatureDelta) {
		mMaxTemperatureDelta = pMaxTemperatureDelta;
	}

}
