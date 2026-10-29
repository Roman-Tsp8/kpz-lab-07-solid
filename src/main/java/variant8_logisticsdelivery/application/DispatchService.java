package variant8_logisticsdelivery.application;

import variant8_logisticsdelivery.domain.Shipment;
import variant8_logisticsdelivery.domain.ShipmentSnapshot;

import java.util.List;

/** Pure Fabrication: оркеструє відправку — доменна зміна стану, збереження, сповіщення. */
public final class DispatchService {
    private final ShipmentRepository repository;
    private final DispatchListener listener;

    public DispatchService(ShipmentRepository repository, DispatchListener listener) {
        this.repository = repository;
        this.listener = listener;
    }

    public ShipmentSnapshot dispatch(Shipment shipment) {
        shipment.dispatch();
        repository.save(shipment);
        var snapshot = shipment.snapshot();
        listener.onDispatched(snapshot);
        return snapshot;
    }

    public List<ShipmentSnapshot> registry() {
        return repository.findAll().stream().map(Shipment::snapshot).toList();
    }
}
