package variant8_logisticsdelivery.application;

import variant8_logisticsdelivery.domain.Shipment;

import java.util.List;

/** Indirection / Protected Variations: деталі сховища приховані за інтерфейсом (реєстр відправок). */
public interface ShipmentRepository {
    void save(Shipment shipment);

    List<Shipment> findAll();
}
