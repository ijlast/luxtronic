package github.ijl.haos.mqtt;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TemperatureSensorTest {
    @Test
    public void listsOnlyTheInitialDashboardTemperatures() {
        List<String> calculations = TemperatureSensor.all().stream().map(TemperatureSensor::calculation).toList();

        Assert.assertEquals(calculations, List.of("ID_WEB_TEMPERATUR_TVL", "ID_WEB_TEMPERATUR_TRL", "ID_WEB_TEMPERATUR_TA", "ID_WEB_TEMPERATUR_TBW", "ID_WEB_EINST_BWS_AKT"));
    }
}
