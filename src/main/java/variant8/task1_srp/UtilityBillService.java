package variant8.task1_srp;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.util.Objects;

/** Оркестратор: лише координує кроки, власної бізнес-логіки не містить. */
public final class UtilityBillService {
    private final MeterReadingValidator validator;
    private final TariffCalculator tariffCalculator;
    private final SubsidyPolicy subsidyPolicy;
    private final InvoiceFormatter invoiceFormatter;
    private final PaymentRegistry paymentRegistry;
    private final BillNotifier billNotifier;
    private final Clock clock;

    public UtilityBillService(MeterReadingValidator validator, TariffCalculator tariffCalculator,
                              SubsidyPolicy subsidyPolicy, InvoiceFormatter invoiceFormatter,
                              PaymentRegistry paymentRegistry, BillNotifier billNotifier, Clock clock) {
        this.validator = Objects.requireNonNull(validator);
        this.tariffCalculator = Objects.requireNonNull(tariffCalculator);
        this.subsidyPolicy = Objects.requireNonNull(subsidyPolicy);
        this.invoiceFormatter = Objects.requireNonNull(invoiceFormatter);
        this.paymentRegistry = Objects.requireNonNull(paymentRegistry);
        this.billNotifier = Objects.requireNonNull(billNotifier);
        this.clock = Objects.requireNonNull(clock);
    }

    /** @return текст квитанції. */
    public String processMonthlyBill(UtilityAccountData account) {
        int consumption = validator.validateAndGetConsumption(account);

        BigDecimal cost = tariffCalculator.calculateCost(consumption);
        BigDecimal deduction = subsidyPolicy.calculateDeduction(account, cost);
        BillCalculation bill = new BillCalculation(consumption, cost, deduction);

        String invoice = invoiceFormatter.format(account, bill);

        // Як і в оригіналі, збій збереження/сповіщення не скасовує вже виконаний розрахунок.
        try {
            paymentRegistry.save(account.getAccountNumber(), bill, LocalDate.now(clock));
        } catch (RuntimeException e) {
            System.err.println("Помилка збереження квитанції: " + e.getMessage());
        }
        try {
            billNotifier.notifyBill(account, bill);
        } catch (RuntimeException e) {
            System.out.println("Помилка відправки SMS-посилання: " + e.getMessage());
        }
        return invoice;
    }
}
