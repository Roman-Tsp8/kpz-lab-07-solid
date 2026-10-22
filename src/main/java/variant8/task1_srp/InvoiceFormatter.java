package variant8.task1_srp;

/** Причина змін №4: вигляд (формат) квитанції. */
public final class InvoiceFormatter {
    private static final String LINE = "==================================================\n";

    public String format(UtilityAccountData account, BillCalculation bill) {
        StringBuilder sb = new StringBuilder();
        sb.append(LINE);
        sb.append("КВИТАНЦІЯ ПРО ОПЛАТУ КОМУНАЛЬНИХ ПОСЛУГ\n");
        sb.append(String.format("Особовий рахунок: %s | ПІБ: %s%n", account.getAccountNumber(), account.getOwnerName()));
        sb.append(String.format("Спожито електроенергії: %d кВт*год%n", bill.consumptionKwh()));
        sb.append(String.format("Нараховано за тарифом: %.2f грн%n", bill.cost()));
        if (account.isHasSubsidy()) {
            sb.append(String.format("Субсидія/Пільга (%s%%): -%.2f грн%n",
                    account.getSubsidyDiscountPercent(), bill.subsidyDeduction()));
        }
        sb.append(String.format("ДО СПЛАТИ: %.2f грн%n", bill.totalToPay()));
        sb.append(LINE);
        return sb.toString();
    }
}
