package github.ijl.luxtronic.format.input;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import github.ijl.luxtronic.config.ServiceProperties;
import github.ijl.luxtronic.exception.DHWTemperatureRangeException;
import github.ijl.luxtronic.format.FormatConverter;

@Service
public class DHWTargetTemperatureConverter implements FormatConverter {
	@Autowired
	private ServiceProperties mProperties;

	/**
	 * @see github.ijl.luxtronic.format.FormatConverter#convertToHeatPumpFormat(java.lang.String)
	 */
	@Override
	public Integer convertToHeatPumpFormat(final String pValue) {
		float value;
		try {
			value = Float.valueOf(pValue);
		} catch (NumberFormatException e) {
			throw new DHWTemperatureRangeException(pValue, mProperties);
		}
		if (value < mProperties.getMinDHWTargetTemperature() || value > mProperties.getMaxDHWTargetTemperature()) {
			throw new DHWTemperatureRangeException(pValue, mProperties);
		}
		return (int) (10 * value);
	}
}
