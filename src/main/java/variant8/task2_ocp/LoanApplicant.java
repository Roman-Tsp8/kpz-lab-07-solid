package variant8.task2_ocp;

import java.math.BigDecimal;
import java.util.Objects;

/** Заявник на кредит (незмінний Value Object із guard-перевірками). */
public final class LoanApplicant {
    private final String applicantId;
    private final int creditScore; // 300-850
    private final BigDecimal monthlyIncome;
    private final BigDecimal requestedAmount;

    public LoanApplicant(String applicantId, int creditScore, BigDecimal monthlyIncome, BigDecimal requestedAmount) {
        this.applicantId = Objects.requireNonNull(applicantId, "applicantId");
        if (creditScore < 300 || creditScore > 850) {
            throw new IllegalArgumentException("Скоринговий бал має бути в межах 300-850: " + creditScore);
        }
        this.creditScore = creditScore;
        this.monthlyIncome = Objects.requireNonNull(monthlyIncome, "monthlyIncome");
        this.requestedAmount = Objects.requireNonNull(requestedAmount, "requestedAmount");
    }

    public String getApplicantId() { return applicantId; }
    public int getCreditScore() { return creditScore; }
    public BigDecimal getMonthlyIncome() { return monthlyIncome; }
    public BigDecimal getRequestedAmount() { return requestedAmount; }
}
