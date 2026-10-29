package variant8_logisticsdelivery.ui;

import variant8_logisticsdelivery.domain.ShipmentSnapshot;

import java.util.Locale;

/** Pure Fabrication: форматування товарно-транспортної накладної (вилучено з доменної моделі). */
public final class WaybillFormatter {

    public String waybill(ShipmentSnapshot s) {
        return "Товарно-транспортна накладна " + s.ttn()
                + "\nВідправник: " + s.senderCompany()
                + "\nТип: " + s.deliveryType()
                + "\nВага: " + money(s.weightKg()) + " кг"
                + "\nТранспортування: " + money(s.transportFee()) + " грн"
                + "\nСтраховка: " + money(s.insuranceFee()) + " грн"
                + "\nЗагальна вартість: " + money(s.totalCost()) + " грн"
                + "\nСтатус: " + s.status();
    }

    public String registryLine(ShipmentSnapshot s) {
        return String.format(Locale.US, "%s | %s | %s | %s грн | %s",
                s.ttn(), s.senderCompany(), s.deliveryType(), money(s.totalCost()), s.status());
    }

    private static String money(double v) {
        return String.format(Locale.US, "%.2f", v);
    }
}
