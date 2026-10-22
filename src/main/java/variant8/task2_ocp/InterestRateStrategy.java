package variant8.task2_ocp;

import java.math.BigDecimal;

/**
 * точка розширення
 * Extension Point системи: нова кредитна програма = нова реалізація цього інтерфейсу.
 * Код {@link #productCode()} — єдине джерело правди про ідентифікатор продукту (замість enum LoanType).
 */
public interface InterestRateStrategy {
    String productCode();

    /** @return річна ставка як частка (0.075 = 7.5%). */
    BigDecimal calculateRate(LoanApplicant applicant);
}
