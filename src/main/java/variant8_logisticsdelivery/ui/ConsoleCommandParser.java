package variant8_logisticsdelivery.ui;

import variant8_logisticsdelivery.application.CreateShipmentCommand;

/**
 * Pure Fabrication: текст з консолі -> DTO. Порожнє поле = значення за замовчуванням із legacy-коду.
 * Лише синтаксичний розбір; бізнес-валідація (вага > 0 тощо) — у домені.
 */
public final class ConsoleCommandParser {
    static final int DEFAULT_SENDER_ID = 55;
    static final String DEFAULT_CONTACT = "Іван Петренко";
    static final String DEFAULT_EMAIL = "logistics@company.ua";
    static final String DEFAULT_TYPE = "Standard";
    static final double DEFAULT_WEIGHT = 120.0;
    static final double DEFAULT_VOLUME = 1.5;
    static final double DEFAULT_VALUE = 50000.0;

    /** @throws IllegalArgumentException якщо число не вдалося розібрати */
    public CreateShipmentCommand parse(String company, String type, String weight, String volume, String value) {
        return new CreateShipmentCommand(DEFAULT_SENDER_ID, nz(company, ""), DEFAULT_CONTACT, DEFAULT_EMAIL,
                nz(type, DEFAULT_TYPE),
                number(weight, DEFAULT_WEIGHT, "вага"),
                number(volume, DEFAULT_VOLUME, "об'єм"),
                number(value, DEFAULT_VALUE, "заявлена вартість"));
    }

    private static String nz(String s, String fallback) {
        return s == null || s.isBlank() ? fallback : s.trim();
    }

    private static double number(String s, double fallback, String field) {
        if (s == null || s.isBlank()) {
            return fallback;
        }
        try {
            return Double.parseDouble(s.trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Поле '" + field + "' має бути числом, отримано: " + s);
        }
    }
}
