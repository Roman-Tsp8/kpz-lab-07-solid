package variant8.task1_srp;

import java.math.BigDecimal;

/** Причина змін №2: тарифна сітка (диференційований тариф). */
public final class TariffCalculator {
    public static final int LOW_TARIFF_LIMIT_KWH = 100;
    public static final BigDecimal LOW_TARIFF = new BigDecimal("2.64");
    public static final BigDecimal HIGH_TARIFF = new BigDecimal("4.32");

    public BigDecimal calculateCost(int consumptionKwh) {
        int lowTariffKwh = Math.min(consumptionKwh, LOW_TARIFF_LIMIT_KWH);
        int highTariffKwh = consumptionKwh - lowTariffKwh;
        return LOW_TARIFF.multiply(BigDecimal.valueOf(lowTariffKwh))
                .add(HIGH_TARIFF.multiply(BigDecimal.valueOf(highTariffKwh)));
    }
}
