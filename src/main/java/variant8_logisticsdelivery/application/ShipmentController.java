package variant8_logisticsdelivery.application;

import variant8_logisticsdelivery.domain.ShipmentSnapshot;

import java.util.List;

/**
 * GRASP Controller (Use-Case Controller). Перший об'єкт після UI.
 * Жодного System.in/out, жодних обчислень — лише делегування.
 */
public final class ShipmentController {
    private final ShipmentFactory factory;
    private final DispatchService dispatcher;

    public ShipmentController(ShipmentFactory factory, DispatchService dispatcher) {
        this.factory = factory;
        this.dispatcher = dispatcher;
    }

    public ShipmentSnapshot registerShipment(CreateShipmentCommand command) {
        return dispatcher.dispatch(factory.create(command));
    }

    public List<ShipmentSnapshot> listShipments() {
        return dispatcher.registry();
    }
}
