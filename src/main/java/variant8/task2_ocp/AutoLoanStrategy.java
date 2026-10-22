package variant8.task2_ocp;

import java.math.BigDecimal;

/** Автокредит: базово 11%, -1.5% при скорингу >= 700. */
public final class AutoLoanStrategy implements InterestRateStrategy {
    private static final BigDecimal BASE = new BigDecimal("0.110");
    private static final BigDecimal DISCOUNT = new BigDecimal("0.015");

    @Override public String productCode() { return "AUTO_LOAN"; }

    @Override
    public BigDecimal calculateRate(LoanApplicant a) {
        return a.getCreditScore() >= 700 ? BASE.subtract(DISCOUNT) : BASE;
    }
}
