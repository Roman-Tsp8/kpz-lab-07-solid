package variant8.task2_ocp;

import java.math.BigDecimal;

/** Студентський: фіксована соціальна ставка 4.5%. */
public final class StudentLoanStrategy implements InterestRateStrategy {
    private static final BigDecimal FIXED = new BigDecimal("0.045");

    @Override public String productCode() { return "STUDENT_LOAN"; }

    @Override
    public BigDecimal calculateRate(LoanApplicant a) { return FIXED; }
}
