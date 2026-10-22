package variant8.task1_srp;

/** Абстракція сповіщення абонента (причина змін №6: канал доставки). */
public interface BillNotifier {
    void notifyBill(UtilityAccountData account, BillCalculation bill);
}
