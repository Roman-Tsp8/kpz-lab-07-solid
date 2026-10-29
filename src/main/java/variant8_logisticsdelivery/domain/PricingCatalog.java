package variant8_logisticsdelivery.domain;

/**
 * Indirection + Protected Variations: єдина точка отримання вартості доставки.
 * Ховає від викликача, як саме обирається тариф і страхове правило.
 */
public interface PricingCatalog {
    /** @throws DomainException якщо тип доставки невідомий */
    Quote quote(String deliveryType, Cargo cargo);
}
