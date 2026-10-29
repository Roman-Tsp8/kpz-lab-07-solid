package variant8_logisticsdelivery.domain;

/** Protected Variations: правило страхування може змінюватись незалежно від ядра. */
public interface InsurancePolicy {
    double premium(Cargo cargo);
}
