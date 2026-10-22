package variant8.task1_srp;

import java.math.BigDecimal;
import java.time.Clock;

public final class Task1Demo {
    public static UtilityBillService createService(PaymentRegistry registry, BillNotifier notifier) {
        return new UtilityBillService(new MeterReadingValidator(), new TariffCalculator(), new SubsidyPolicy(),
                new InvoiceFormatter(), registry, notifier, Clock.systemDefaultZone());
    }

    public static void run() {
        UtilityAccountData acc = new UtilityAccountData();
        acc.setAccountNumber("ACC-1001");
        acc.setOwnerName("Іваненко І.І.");
        acc.setOwnerPhone("+380501234567");
        acc.setPreviousElectricityMeterReading(1000);
        acc.setCurrentElectricityMeterReading(1250);
        acc.setHasSubsidy(true);
        acc.setSubsidyDiscountPercent(new BigDecimal("20"));

        UtilityBillService service = createService(new CsvPaymentRegistry("utility_payments_db.csv"), new SmsBillNotifier());
        System.out.println(service.processMonthlyBill(acc));
    }
}
