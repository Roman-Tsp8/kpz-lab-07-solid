package variant8.task1_srp;

import java.util.Locale;

/** Формує SMS з посиланням на онлайн-оплату (відправка імітується виводом у консоль). */
public final class SmsBillNotifier implements BillNotifier {
    private static final String GATEWAY_URL = "http://sms-gateway.local/send";
    private static final String PAYMENT_URL = "http://pay.utility.local/bill";

    @Override
    public void notifyBill(UtilityAccountData account, BillCalculation bill) {
        String paymentUrl = String.format(Locale.ROOT, "%s?acc=%s&sum=%.2f",
                PAYMENT_URL, account.getAccountNumber(), bill.totalToPay());
        String sms = String.format("Рахунок за комунальні послуги: %.2f грн. Оплата онлайн: %s",
                bill.totalToPay(), paymentUrl);
        System.out.println("Надсилання SMS на " + account.getOwnerPhone() + " через " + GATEWAY_URL);
        System.out.println("Текст: " + sms);
    }
}
