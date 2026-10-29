package variant8_logisticsdelivery.infrastructure;

import variant8_logisticsdelivery.application.DispatchListener;
import variant8_logisticsdelivery.domain.ShipmentSnapshot;

import java.util.List;

/** Composite: кілька слухачів виглядають для сервісу як один. */
public final class CompositeDispatchListener implements DispatchListener {
    private final List<DispatchListener> listeners;

    public CompositeDispatchListener(List<DispatchListener> listeners) {
        this.listeners = List.copyOf(listeners);
    }

    @Override
    public void onDispatched(ShipmentSnapshot shipment) {
        listeners.forEach(l -> l.onDispatched(shipment));
    }
}
