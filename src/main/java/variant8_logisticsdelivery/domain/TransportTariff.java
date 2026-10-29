package variant8_logisticsdelivery.domain;

/** Protected Variations: стабільний контракт для змінної частини — алгоритму тарифікації. */
public interface TransportTariff {
    String name();

    double transportFee(Cargo cargo);
}
