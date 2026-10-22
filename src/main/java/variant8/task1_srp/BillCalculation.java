package variant8.task1_srp;

import java.math.BigDecimal;

/** Незмінний результат розрахунку (Value Object). */
public record BillCalculation(int consumptionKwh, BigDecimal cost, BigDecimal subsidyDeduction) {

    public BigDecimal totalToPay() {
        return cost.subtract(subsidyDeduction);
    }
}
