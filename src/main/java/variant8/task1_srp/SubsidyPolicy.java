package variant8.task1_srp;

import java.math.BigDecimal;

/** Причина змін №3: правила пільг та субсидій. */
public final class SubsidyPolicy {
    private static final BigDecimal HUNDRED = new BigDecimal("100.0");

    public BigDecimal calculateDeduction(UtilityAccountData account, BigDecimal cost) {
        if (!account.isHasSubsidy()) {
            return BigDecimal.ZERO;
        }
        BigDecimal percent = account.getSubsidyDiscountPercent();
        if (percent == null || percent.signum() <= 0) {
            return BigDecimal.ZERO;
        }
        return cost.multiply(percent.divide(HUNDRED));
    }
}
