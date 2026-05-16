package github.ijl.luxtronic;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import github.ijl.luxtronic.config.v161.Calculations;
import github.ijl.luxtronic.config.v161.EnumConstantsMap;
import github.ijl.luxtronic.config.v161.Parameters;
import github.ijl.luxtronic.exception.InvalidParameterException;
import github.ijl.luxtronic.exception.ParameterRuntimeException;
import github.ijl.luxtronic.format.FormatConverter;
import github.ijl.luxtronic.format.OneToOneConverter;
import github.ijl.luxtronic.param.DomesicHotWaterParameter;
import github.ijl.luxtronic.param.HeatingParameter;

@RestController
@RequestMapping("luxtronic")
@Slf4j
public class HeatPumpController {
    private final ApplicationContext applicationContext;
    private final HeatPumpSocketWrapper heatPumpSocketWrapper;

    private final Map<Integer, Calculations> calculationsMap = EnumConstantsMap.buildFrom(Calculations.class);
    private final Map<Integer, Parameters> parametersMap = EnumConstantsMap.buildFrom(Parameters.class);

    @Autowired
    public HeatPumpController(ApplicationContext applicationContext, HeatPumpSocketWrapper heatPumpSocketWrapper) {
        this.applicationContext = applicationContext;
        this.heatPumpSocketWrapper = heatPumpSocketWrapper;
    }

    /**
     * To read parameters send 3003 0000 (0x00 0x00 0x0b 0xbb 0x00 0x00 0x00 0x00)
     * The Luxtronik responds with the command (4 bytes) and the number of
     * parameters that follow (4 bytes), also formatted big endian.
     *
     * @return
     */
    @GetMapping(path = "/parameters", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> parameters() {
        log.debug("/parameters called!");
        return getParameters(3003, false, 0);
    }

    /**
     * To read parameters send 3003 0000 (0x00 0x00 0x0b 0xbb 0x00 0x00 0x00 0x00)
     * The Luxtronik responds with the command (4 bytes) and the number of
     * parameters that follow (4 bytes), also formatted big endian.
     *
     * @return
     */
    @GetMapping(path = "/parameter/{parameter}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String parameter(final @PathVariable("parameter") String pParameter) {
        log.debug("/parameter/" + pParameter + " called!");
        final Map<String, String> result = getParameters(3003, false, 0);
        return result.get(pParameter);
    }

    /**
     * To read parameters send 3003 0000 (0x00 0x00 0x0b 0xbb 0x00 0x00 0x00 0x00)
     * The Luxtronik responds with the command (4 bytes) and the number of
     * parameters that follow (4 bytes), also formatted big endian.
     *
     * @return
     */
    @GetMapping(path = "/calculations", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> calculations() {
        log.debug("/calcuations called!");
        return getParameters(3004, true, 10);
    }

    /**
     * To read parameters send 3003 0000 (0x00 0x00 0x0b 0xbb 0x00 0x00 0x00 0x00)
     * The Luxtronik responds with the command (4 bytes) and the number of
     * parameters that follow (4 bytes), also formatted big endian.
     *
     * @return
     */
    @GetMapping(path = "/calculation/{parameter}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String calculation(final @PathVariable("parameter") String pParameter) {
        log.debug("/calculation/" + pParameter + " called!");
        final Map<String, String> result = getParameters(3004, true, 10);
        return result.get(pParameter);
    }

    /**
     * Set the heating parameters
     *
     * @param pParameter
     *            one of <quote>MODE</quote> or <quote>TemperatureDelta</quote>
     * @param pValue
     *            on of the <code>OperatingMode</code> enums or a temperature
     *            offset.
     * @return HTTP status
     */
    @PutMapping(path = "/heating/{parameter}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String heating(@PathVariable("parameter") String pParameter, @RequestBody String pValue) {
        log.debug("/heating/" + pParameter + " called with value: " + pValue);
        try {
            final HeatingParameter parameter = HeatingParameter.valueOf(pParameter);
            final Class<? extends FormatConverter> convClass = parameter.getFormatConverterClass();
            return setParameter(parameter, parameter.getIntegerValue(), convClass, pValue);
        } catch (IllegalArgumentException _) {
            throw new InvalidParameterException(pParameter, HeatingParameter.class);
        }
    }

    /**
     * Set the hotwater parameters
     *
     * @param pParameter
     *            one of <quote>MODE</quote> or <quote>TemperatureDelta</quote>
     * @param pValue
     *            on of the <code>OperatingMode</code> enums or a temperature
     *            offset.
     * @return HTTP status
     */
    @PutMapping(path = "/hotwater/{parameter}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String hotwater(@PathVariable("parameter") String pParameter, @RequestBody String pValue) {
        log.debug("/hotwater/" + pParameter + " called with value: " + pValue);
        try {
            final DomesicHotWaterParameter parameter = DomesicHotWaterParameter.valueOf(pParameter);
            final Class<? extends FormatConverter> convClass = parameter.getFormatConverterClass();
            return setParameter(parameter, parameter.getIntegerValue(), convClass, pValue);
        } catch (IllegalArgumentException _) {
            throw new InvalidParameterException(pParameter, DomesicHotWaterParameter.class);
        }
    }

    /**
     * Common code for setting heat pump operating mode or temperature delta
     * parameters. synchronized as there is only one connection to the heatpump in
     * this version!
     */
    private String setParameter(final Enum<?> pParameter, final Integer pEnumValue, final @NonNull Class<? extends FormatConverter> pConverter, final String pValue) {
        HttpStatus status = HttpStatus.OK;

        log.debug("Parameter: " + pParameter.name());
        log.debug("Converter class: " + pConverter);
        log.debug("value: " + pValue);

        try {
            final FormatConverter converter = applicationContext.getBean(pConverter);
            // shouldn't be null
            final Integer value = converter.convertToHeatPumpFormat(pValue);
            log.debug("Converted value: " + value);

            synchronized (heatPumpSocketWrapper) {
                heatPumpSocketWrapper.write(3002, pEnumValue, value);
                heatPumpSocketWrapper.read(2);
            }
        } catch (Exception e) {
            log.error("setParameter: Exception Writing Parameter", e);
            throw new ParameterRuntimeException("setParameter: Exception Writing Parameter", e);
        }
        return status.toString();
    }

    /**
     * Common code for reading parameters and calculations.synchronized as there is
     * only one connection to the heatpump in this version!
     *
     * @param pCommand
     *            command to send
     * @param pReadStatus
     *            whether or not to expect the extra status information when reading
     *            response.
     * @return data read from the server.
     */
    private Map<String, String> getParameters(final int pCommand, final boolean pReadStatus, final int pSkip) {
        Map<String, String> result = Collections.emptyMap();
        try {
            synchronized (heatPumpSocketWrapper) {
                heatPumpSocketWrapper.write(pCommand, 0);
                final ByteBuffer output = heatPumpSocketWrapper.read(pReadStatus);
                output.position(pSkip * HeatPumpSocketWrapper.BYTES_PER_INT);
                result = byteBufferToMap(output, pReadStatus);
            }
        } catch (final Exception e) {
            log.error("Exception Reading Parameter", e);
            throw new ParameterRuntimeException("getParameter: Exception Reading Parameter", e);
        }
        return result;
    }

    private Map<String, String> byteBufferToMap(final ByteBuffer pBuffer, boolean pUseCalculations) {
        final Map<String, String> dataMap = new LinkedHashMap<>();
        for (int i = pBuffer.position(); i < pBuffer.limit(); i += HeatPumpSocketWrapper.BYTES_PER_INT) {
            int index = i / HeatPumpSocketWrapper.BYTES_PER_INT;
            String name = Integer.toString(index);
            Class<? extends FormatConverter> convClass = OneToOneConverter.class;

            // Switch between parameters and calculations
            if (pUseCalculations) {
                final Calculations calc = calculationsMap.get(index);
                if (calc != null) {
                    convClass = calc.getFormatConverterClass();
                    name = calc.name();
                }
            } else {
                final Parameters param = parametersMap.get(index);
                if (param != null) {
                    convClass = param.getFormatConverterClass();
                    name = param.name();
                }
            }

            final FormatConverter conv = applicationContext.getBean(convClass);
            dataMap.put(name, conv.convertToHumanReadable(pBuffer.getInt()));
        }

        return dataMap;
    }
}
