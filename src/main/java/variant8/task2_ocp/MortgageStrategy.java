package variant8.task2_ocp;

import java.math.BigDecimal;

/** Іпотека: базово 7.5%, знижка 1.0% при скорингу > 750, надбавка 1.5% при скорингу < 650. */
public final class MortgageStrategy implements InterestRateStrategy {
    private static final BigDecimal BASE = new BigDecimal("0.075");
    private static final BigDecimal DISCOUNT = new BigDecimal("0.010");
    private static final BigDecimal SURCHARGE = new BigDecimal("0.015");

    @Override public String productCode() { return "MORTGAGE"; }

    @Override
    public BigDecimal calculateRate(LoanApplicant a) {
        if (a.getCreditScore() > 750) return BASE.subtract(DISCOUNT);
        if (a.getCreditScore() < 650) return BASE.add(SURCHARGE);
        return BASE;
    }
}
