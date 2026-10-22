package variant8.task2_ocp;

import java.math.BigDecimal;
import java.util.List;

public final class Task2Demo {
    public static LoanInterestCalculator createCalculator() {
        return new LoanInterestCalculator(List.of(new MortgageStrategy(), new ConsumerLoanStrategy(),
                new AutoLoanStrategy(), new StudentLoanStrategy(), new BusinessLoanStrategy()));
    }

    public static void run() {
        LoanInterestCalculator calc = createCalculator();
        LoanApplicant a = new LoanApplicant("CL-77", 780, new BigDecimal("3500"), new BigDecimal("100000"));
        for (String code : List.of("MORTGAGE", "CONSUMER_LOAN", "AUTO_LOAN", "STUDENT_LOAN", "BUSINESS_LOAN")) {
            calc.calculateInterestRate(a, code);
        }
    }
}
