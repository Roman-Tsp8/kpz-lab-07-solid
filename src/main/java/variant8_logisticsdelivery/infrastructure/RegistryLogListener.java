package variant8_logisticsdelivery.infrastructure;

import variant8_logisticsdelivery.application.DispatchListener;
import variant8_logisticsdelivery.domain.ShipmentSnapshot;

import java.io.PrintStream;

/** Pure Fabrication: журнал/аудит запису у реєстр. */
public final class RegistryLogListener implements DispatchListener {
    private final PrintStream out;

    public RegistryLogListener(PrintStream out) {
        this.out = out;
    }

    @Override
    public void onDispatched(ShipmentSnapshot shipment) {
        out.println("[Реєстр] Накладну " + shipment.ttn() + " додано у базу відправок.");
    }
}
