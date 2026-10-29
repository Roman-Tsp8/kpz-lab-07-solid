package variant8_logisticsdelivery.domain;

/** Незмінний read-model відправки для шарів поза доменом (щоб вони не чіпали внутрішній стан). */
public record ShipmentSnapshot(int trackingId, String senderCompany, String senderEmail,
                               String deliveryType, double weightKg, double transportFee, double insuranceFee,
                               double totalCost, String status) {

    public String ttn() {
        return "TTN-" + trackingId;
    }
}
