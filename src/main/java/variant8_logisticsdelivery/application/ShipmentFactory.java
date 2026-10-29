package variant8_logisticsdelivery.application;

import variant8_logisticsdelivery.domain.PricingCatalog;
import variant8_logisticsdelivery.domain.Shipment;

/** Pure Fabrication: збирає агрегат із команди, комбінуючи сервіси (тарифи, генератор id). */
public final class ShipmentFactory {
    private final PricingCatalog pricing;
    private final TrackingIdGenerator ids;

    public ShipmentFactory(PricingCatalog pricing, TrackingIdGenerator ids) {
        this.pricing = pricing;
        this.ids = ids;
    }

    public Shipment create(CreateShipmentCommand command) {
        var cargo = command.toCargo();
        return Shipment.create(ids.nextId(), command.toSender(), cargo,
                pricing.quote(command.deliveryType(), cargo));
    }
}
