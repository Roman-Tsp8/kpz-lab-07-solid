package variant8.task2_ocp;

import java.math.BigDecimal;

/**
 * ДЕМОНСТРАЦІЯ OCP: новий продукт "Бізнес-кредит" (базово 13%, -1% при скорингу >= 720).
 * Додано окремим файлом; LoanInterestCalculator та інші стратегії не змінювались.
 */
public final class BusinessLoanStrategy implements InterestRateStrategy {
    private static final BigDecimal BASE = new BigDecimal("0.130");
    private static final BigDecimal DISCOUNT = new BigDecimal("0.010");

    @Override public String productCode() { return "BUSINESS_LOAN"; }

    @Override
    public BigDecimal calculateRate(LoanApplicant a) {
        return a.getCreditScore() >= 720 ? BASE.subtract(DISCOUNT) : BASE;
    }
}
