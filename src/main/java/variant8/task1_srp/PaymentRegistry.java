package variant8.task1_srp;

import java.time.LocalDate;

/** Абстракція реєстру платежів (причина змін №5: спосіб збереження). */
public interface PaymentRegistry {
    void save(String accountNumber, BillCalculation bill, LocalDate date);
}
