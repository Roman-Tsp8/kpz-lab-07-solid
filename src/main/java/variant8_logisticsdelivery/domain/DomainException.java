package variant8_logisticsdelivery.domain;

/** Порушення бізнес-інваріанта домену (невалідні дані, невідомий тип доставки тощо). */
public class DomainException extends IllegalArgumentException {
    public DomainException(String message) {
        super(message);
    }
}
