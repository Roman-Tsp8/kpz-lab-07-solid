package variant8.task2_ocp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Закритий для модифікації: не знає жодного конкретного продукту.
 * Нові продукти додаються передачею ще однієї стратегії в конструктор.
 */
public final class LoanInterestCalculator {
    private final Map<String, InterestRateStrategy> strategies = new HashMap<>();

    public LoanInterestCalculator(List<InterestRateStrategy> strategies) {
        Objects.requireNonNull(strategies, "strategies");
        for (InterestRateStrategy s : strategies) {
            if (this.strategies.putIfAbsent(s.productCode(), s) != null) {
                throw new IllegalArgumentException("Дублікат кредитного продукту: " + s.productCode());
            }
        }
    }

    public BigDecimal calculateInterestRate(LoanApplicant applicant, String productCode) {
        Objects.requireNonNull(applicant, "applicant");
        InterestRateStrategy strategy = strategies.get(productCode);
        if (strategy == null) {
            throw new IllegalArgumentException("Невідомий тип кредиту: " + productCode);
        }
        BigDecimal rate = strategy.calculateRate(applicant).setScale(4, RoundingMode.HALF_UP);
        System.out.println("[LOAN] Заявка від " + applicant.getApplicantId() + " (Скоринг: "
                + applicant.getCreditScore() + "): Продукт=" + productCode
                + " -> Ставка: " + rate.multiply(new BigDecimal("100")) + "%");
        return rate;
    }
}
