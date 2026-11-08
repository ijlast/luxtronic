# Luxtronic heat-pump bridge

This Spring Boot service provides a REST API for reading data from, and changing
selected settings on, a Luxtronic heat-pump controller. An optional MQTT
integration publishes temperature readings for Home Assistant.

The REST API is the standalone bridge's core interface, not an HC2-only
adapter. The original Fibaro Home Center 2 panel called it; there are no
currently active consumers beyond direct API use for testing and debugging
(for example, with Postman). The API remains useful for diagnostics and
parameter updates, while integrations such as Home Assistant are optional
extensions. Additional protection for write operations is future work, so do
not expose those endpoints to untrusted networks.

## Protocol scope and compatibility

The protocol mappings in this repository are organized under `config/v161`
and target the Luxtronik II v1.6.1 firmware used with this installation. The
primary reference is the LoxWiki article on the [Java Webinterface protocol](https://loxwiki.atlassian.net/wiki/spaces/LOX/pages/1533935933/Java+Webinterface).
Its documented command numbers, big-endian 32-bit integer framing, calculation
indices, and temperature scaling (raw value divided by 10) closely match this
implementation. The calculation mappings in `Calculations` begin at index 10,
consistent with the article's note that indices 0–10 are unclear and its table
starts at 10.

The article describes the legacy protocol used by the Luxtronik 2 Java applet,
which remains active on later controller software. It notes port 8889 as the
usual port and port 8888 for Luxtronik 2 modules with software versions below
1.76. This service defaults to port 8888, matching the reported older-module
case; verify the configured port against the controller if the connection
fails.

The mapping is grounded in that protocol reference, but available values and
their meaning can still vary with controller firmware and installed equipment.
The article itself cautions that some indices are unclear and that values for
uninstalled hardware may still appear. Treat unmapped values and model-specific
interpretations accordingly. The `coolchip/luxtronik2` project is a useful
comparison for Luxtronik 2.0, not the primary source for this v1.6.1 mapping.

## REST API

The API is rooted at `/luxtronic`. `GET` endpoints return converted values as
strings, with maps keyed by the calculation or parameter enum names when a
mapping is known.

| Method | Path | Purpose |
| --- | --- | --- |
| `GET` | `/luxtronic/parameters` | Read the controller's parameter values |
| `GET` | `/luxtronic/parameter/{parameter}` | Read one parameter by its enum name |
| `GET` | `/luxtronic/calculations` | Read the controller's calculation values |
| `GET` | `/luxtronic/calculation/{parameter}` | Read one calculation by its enum name |
| `PUT` | `/luxtronic/heating/{parameter}` | Change a supported heating setting |
| `PUT` | `/luxtronic/hotwater/{parameter}` | Change a supported hot-water setting |

Supported heating settings are `TemperatureDelta` and `Mode`. Supported hot
water settings are `TargetTemperature` and `Mode`. Mode values are
`AUTOMATIC`, `AUXILIARY_HEATER`, `PARTY`, `HOLIDAY`, and `OFF`. The PUT request
body is a string; use `application/json`, for example `"AUTOMATIC"` for a mode.
Temperature inputs are in degrees Celsius and are checked against configured
bounds.

## Configuration

Heat-pump connection and setting bounds are configured in
[`src/main/resources/application.yml`](src/main/resources/application.yml) and
can be overridden using Spring Boot environment variables:

| Environment variable | Purpose | Default in `application.yml` |
| --- | --- | --- |
| `HEATPUMP_IP` | Controller IP address | `192.168.178.6` |
| `HEATPUMP_PORT` | Controller TCP port | `8888` |
| `HEATPUMP_MIN_DHW_TARGET_TEMPERATURE` | Minimum writable hot-water target temperature | `30` |
| `HEATPUMP_MAX_DHW_TARGET_TEMPERATURE` | Maximum writable hot-water target temperature | `50` |
| `HEATPUMP_MIN_TEMPERATURE_DELTA` | Minimum writable heating temperature delta | `-5` |
| `HEATPUMP_MAX_TEMPERATURE_DELTA` | Maximum writable heating temperature delta | `5` |

Change the example IP address to match the controller on your network. The
temperature limits are application-side validation bounds; they should be set
to values appropriate for the heat pump.

## Home Assistant via MQTT

The optional `haos` Spring profile publishes MQTT sensor discovery and
temperature states. This integration is still under development. It is
intended to expose selected heat-pump measurements on a Home Assistant
dashboard. The initial sensor set is heating-circuit flow and return
temperatures, outside temperature, hot-water actual temperature, and hot-water
set temperature. Other mapped temperature calculations are not published
unless added to the sensor list. Home Assistant is not called directly: both
systems must be able to connect to the same MQTT broker, such as the Mosquitto
add-on configured in Home Assistant.

Set `SPRING_PROFILES_ACTIVE=haos` and configure the broker:

| Environment variable | Purpose | Default |
| --- | --- | --- |
| `MQTT_HOST` | MQTT broker hostname or IP address reachable by this service | `localhost` |
| `MQTT_PORT` | MQTT broker port | `1883` |
| `MQTT_USERNAME` | Broker username, if required | None |
| `MQTT_PASSWORD` | Broker password, if required | None |
| `MQTT_BASE_TOPIC` | Home Assistant discovery topic prefix | `homeassistant` |
| `MQTT_CLIENT_ID` | MQTT client identifier | Generated at startup |
| `LUXTRONIC_POLL_INTERVAL_MS` | Poll interval in milliseconds | `300000` (5 minutes) |

The integration discovers the five selected temperature sensors and publishes
their converted Celsius values under
`luxtronic/sensor/<calculation-name>/state`. State is refreshed every five
minutes by default; the interval is configurable. Discovery configuration is
retained, while state messages are not retained. Discovered sensors expire
after 10 minutes without an update. Home Assistant history and long-term
statistics also depend on its Recorder settings.

Without the `haos` profile, the application does not create an MQTT connection;
the REST API remains available.

To run the bridge as a Home Assistant OS app on a Raspberry Pi 4, follow the
[HAOS app installation guide](haos/luxtronic/README.md).

## Running

Build and test with the Gradle wrapper:

```powershell
.\gradlew.bat test
```

Run locally:

```powershell
.\gradlew.bat bootRun
```

The service listens on port 8080 by default. A runnable JAR can be created with
`.\gradlew.bat bootJar`.

## References

* **Primary protocol reference:** [LoxWiki: Java Webinterface](https://loxwiki.atlassian.net/wiki/spaces/LOX/pages/1533935933/Java+Webinterface) — legacy TCP commands, packet framing, calculation indices, and value conversions.
* [coolchip/luxtronik2](https://github.com/coolchip/luxtronik2) — a Node.js project for Luxtronik 2.0 that references the openHAB binding; useful for comparison, but not a Home Assistant integration.
* [Alpha-Innotec controller documentation (PDF)](https://www.alpha-innotec.cz/wp-content/uploads/2019/03/controller.pdf)
* [AIT Group documentation (PDF)](https://mw.ait-group.net/files/docs/EN/A0220/83055400.pdf)
