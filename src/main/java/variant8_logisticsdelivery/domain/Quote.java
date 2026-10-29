package variant8_logisticsdelivery.domain;

/** Розрахована вартість доставки: тип, транспортування, страхування. */
public record Quote(String deliveryType, double transportFee, double insuranceFee) {

    public double total() {
        return transportFee + insuranceFee;
    }
}
