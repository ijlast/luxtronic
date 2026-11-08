# Luxtronic Bridge app

Runs the Luxtronic bridge as a Home Assistant OS app on supported
architectures, including the Raspberry Pi 4 (64-bit).

## Install

1. In Home Assistant, open **Settings → Apps → Install app**.
2. Open the menu and choose **Repositories**.
3. Add `https://github.com/ijlast/luxtronic`.
4. Install **Luxtronic Bridge**.
5. In its **Configuration** tab, enter the MQTT password for the dedicated
   `luxtronic_bridge` Home Assistant user and check the heat-pump IP address.
6. Save, start the app, and enable **Start on boot** if it is not already on.

The app connects to Mosquitto at `core-mosquitto:1883`, the internal HAOS
hostname for the official Mosquitto broker app. Keep the MQTT username and
password aligned with the broker's configured credentials.

The app image is published to GHCR by this repository's Docker workflow. The
package must be public so Home Assistant can pull it. If the Supervisor cannot
pull the image, check the package visibility under the repository's GitHub
Packages settings.

## Defaults

The app is configured for the current installation: heat-pump address
`192.168.178.6`, protocol port `8888`, and a five-minute MQTT polling interval.
Change these under the app's configuration before starting if your network or
heat-pump settings differ.

The MQTT discovery topic prefix defaults to `homeassistant`. State topics use
the fixed prefix `luxtronic/sensor/`.

The REST API listens on port 8080 inside the app. It is not exposed on the
network by default; enable the optional port mapping in the app's **Network**
settings only when needed. The API includes write endpoints and currently has
no authentication, so do not expose it to untrusted networks.

## Logs and troubleshooting

Check the app's **Log** tab for MQTT connection, discovery, polling, or
heat-pump errors. Home Assistant should discover the **Luxtronic Heat Pump**
device after startup. Initial state values are published on the first poll;
subsequent updates follow the configured interval.
