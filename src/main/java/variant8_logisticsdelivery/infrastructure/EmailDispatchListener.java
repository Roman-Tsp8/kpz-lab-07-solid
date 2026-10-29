package variant8_logisticsdelivery.infrastructure;

import variant8_logisticsdelivery.application.DispatchListener;
import variant8_logisticsdelivery.domain.ShipmentSnapshot;

import java.io.PrintStream;

/** Імітація email-шлюзу. Реальний SMTP/SendGrid підміняється іншою реалізацією (Protected Variations). */
public final class EmailDispatchListener implements DispatchListener {
    private final PrintStream out;

    public EmailDispatchListener(PrintStream out) {
        this.out = out;
    }

    @Override
    public void onDispatched(ShipmentSnapshot shipment) {
        out.println("[Email] Трекінг-код " + shipment.ttn() + " надіслано на " + shipment.senderEmail());
    }
}
