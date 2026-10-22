package variant8.task1_srp;

import java.util.Objects;

/** Причина змін №1: правила валідації даних лічильника. */
public final class MeterReadingValidator {
    public static final int MAX_MONTHLY_CONSUMPTION_KWH = 5000;

    /** @return спожиті кВт*год; кидає виняток, якщо дані некоректні (fail-fast). */
    public int validateAndGetConsumption(UtilityAccountData account) {
        Objects.requireNonNull(account, "account");

        if (account.getAccountNumber() == null || account.getAccountNumber().isBlank()) {
            throw new IllegalArgumentException("Номер особового рахунку обов'язковий.");
        }
        if (account.getCurrentElectricityMeterReading() < account.getPreviousElectricityMeterReading()) {
            throw new IllegalStateException("Поточні показники лічильника не можуть бути меншими за попередні.");
        }

        int consumptionKwh = account.getCurrentElectricityMeterReading()
                - account.getPreviousElectricityMeterReading();

        if (consumptionKwh > MAX_MONTHLY_CONSUMPTION_KWH) {
            throw new IllegalArgumentException(
                    "Аномально високе споживання електроенергії. Потрібна перевірка інспектора.");
        }
        return consumptionKwh;
    }
}
