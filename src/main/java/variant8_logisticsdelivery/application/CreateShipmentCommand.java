package variant8_logisticsdelivery.application;

import variant8_logisticsdelivery.domain.Cargo;
import variant8_logisticsdelivery.domain.Sender;

/** DTO-запит від UI до контролера. Не містить бізнес-логіки, лише перетворення у доменні об'єкти. */
public record CreateShipmentCommand(int senderId, String companyName, String contactPerson, String email,
                                    String deliveryType, double weightKg, double volumeCubMetres,
                                    double declaredValue) {

    Sender toSender() {
        return new Sender(senderId, companyName, contactPerson, email);
    }

    Cargo toCargo() {
        return new Cargo(weightKg, volumeCubMetres, declaredValue);
    }
}
