package variant8_logisticsdelivery.infrastructure;

import variant8_logisticsdelivery.application.ShipmentRepository;
import variant8_logisticsdelivery.domain.Shipment;

import java.util.ArrayList;
import java.util.List;

/** Реєстр відправок у пам'яті. Заміна на БД не зачепить ні домен, ні контролер. */
public final class InMemoryShipmentRepository implements ShipmentRepository {
    private final List<Shipment> storage = new ArrayList<>();

    @Override
    public void save(Shipment shipment) {
        storage.add(shipment);
    }

    @Override
    public List<Shipment> findAll() {
        return List.copyOf(storage);
    }
}
