package variant8.task2_ocp;

import java.math.BigDecimal;

/** Споживчий: базово 18%, -2% при доході >= 3000, +4% при скорингу < 600. */
public final class ConsumerLoanStrategy implements InterestRateStrategy {
    private static final BigDecimal BASE = new BigDecimal("0.180");
    private static final BigDecimal HIGH_INCOME_THRESHOLD = new BigDecimal("3000.00");
    private static final BigDecimal HIGH_INCOME_DISCOUNT = new BigDecimal("0.020");
    private static final BigDecimal LOW_SCORE_SURCHARGE = new BigDecimal("0.040");

    @Override public String productCode() { return "CONSUMER_LOAN"; }

    @Override
    public BigDecimal calculateRate(LoanApplicant a) {
        BigDecimal rate = BASE;
        if (a.getMonthlyIncome().compareTo(HIGH_INCOME_THRESHOLD) >= 0) rate = rate.subtract(HIGH_INCOME_DISCOUNT);
        if (a.getCreditScore() < 600) rate = rate.add(LOW_SCORE_SURCHARGE);
        return rate;
    }
}
