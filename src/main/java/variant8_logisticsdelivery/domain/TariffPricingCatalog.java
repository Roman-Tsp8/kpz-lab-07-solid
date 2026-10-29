package variant8_logisticsdelivery.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Dictionary Dispatch замість if-else/switch: новий тип доставки = новий клас-тариф + рядок реєстрації,
 * жодного редагування існуючої логіки (Open/Closed).
 */
public final class TariffPricingCatalog implements PricingCatalog {
    private final Map<String, TransportTariff> tariffs = new HashMap<>();
    private final InsurancePolicy insurance;

    public TariffPricingCatalog(List<TransportTariff> tariffs, InsurancePolicy insurance) {
        for (TransportTariff t : tariffs) {
            this.tariffs.put(t.name().toLowerCase(Locale.ROOT), t);
        }
        this.insurance = insurance;
    }

    @Override
    public Quote quote(String deliveryType, Cargo cargo) {
        String key = deliveryType == null ? "" : deliveryType.trim().toLowerCase(Locale.ROOT);
        TransportTariff tariff = tariffs.get(key);
        if (tariff == null) {
            throw new DomainException("Невідомий тип доставки: '" + deliveryType
                    + "'. Доступні: " + String.join(", ", availableTypes()));
        }
        return new Quote(tariff.name(), tariff.transportFee(cargo), insurance.premium(cargo));
    }

    private List<String> availableTypes() {
        return tariffs.values().stream().map(TransportTariff::name).sorted().toList();
    }
}
