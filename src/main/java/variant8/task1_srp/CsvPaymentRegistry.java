package variant8.task1_srp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/** Зберігає платіж у CSV-файл (дописує в кінець). */
public final class CsvPaymentRegistry implements PaymentRegistry {
    private final String filePath;

    public CsvPaymentRegistry(String filePath) {
        this.filePath = java.util.Objects.requireNonNull(filePath, "filePath");
    }

    @Override
    public void save(String accountNumber, BillCalculation bill, LocalDate date) {
        String line = String.format(Locale.ROOT, "%s;%d;%.2f;%s%n", accountNumber, bill.consumptionKwh(),
                bill.totalToPay(), date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        try (FileOutputStream fos = new FileOutputStream(filePath, true)) {
            fos.write(line.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
