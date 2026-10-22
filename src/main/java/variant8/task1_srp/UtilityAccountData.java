package variant8.task1_srp;

import java.math.BigDecimal;

/** Доменна сутність: дані особового рахунку та показники лічильника (лише дані, без логіки). */
public class UtilityAccountData {
    private String accountNumber;
    private String ownerName;
    private String ownerPhone;
    private int previousElectricityMeterReading;
    private int currentElectricityMeterReading;
    private boolean hasSubsidy;
    private BigDecimal subsidyDiscountPercent;

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getOwnerPhone() { return ownerPhone; }
    public void setOwnerPhone(String ownerPhone) { this.ownerPhone = ownerPhone; }

    public int getPreviousElectricityMeterReading() { return previousElectricityMeterReading; }
    public void setPreviousElectricityMeterReading(int v) { this.previousElectricityMeterReading = v; }

    public int getCurrentElectricityMeterReading() { return currentElectricityMeterReading; }
    public void setCurrentElectricityMeterReading(int v) { this.currentElectricityMeterReading = v; }

    public boolean isHasSubsidy() { return hasSubsidy; }
    public void setHasSubsidy(boolean hasSubsidy) { this.hasSubsidy = hasSubsidy; }

    public BigDecimal getSubsidyDiscountPercent() { return subsidyDiscountPercent; }
    public void setSubsidyDiscountPercent(BigDecimal p) { this.subsidyDiscountPercent = p; }
}
