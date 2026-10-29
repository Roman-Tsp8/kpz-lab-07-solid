package variant8_logisticsdelivery.application;

import variant8_logisticsdelivery.domain.ShipmentSnapshot;

/** Indirection: реакція на відправку (запис у реєстр-лог, email, аудит) без прив'язки сервісу до реалізацій. */
public interface DispatchListener {
    void onDispatched(ShipmentSnapshot shipment);
}
