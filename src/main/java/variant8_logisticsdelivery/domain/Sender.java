package variant8_logisticsdelivery.domain;

/** Відправник. Імутабельний value object; валідний стан гарантується конструктором. */
public record Sender(int id, String companyName, String contactPerson, String email) {

    public Sender {
        if (companyName == null || companyName.isBlank()) {
            throw new DomainException("Назва компанії-відправника не може бути порожньою.");
        }
        if (contactPerson == null || contactPerson.isBlank()) {
            throw new DomainException("Контактна особа обов'язкова.");
        }
        if (email == null || !email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new DomainException("Некоректний email відправника: " + email);
        }
        companyName = companyName.trim();
    }
}
