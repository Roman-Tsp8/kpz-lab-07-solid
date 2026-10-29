package variant8_logisticsdelivery.domain;

import java.util.Objects;

/**
 * Aggregate Root. Rich Domain Model:
 *  - Creator: об'єкт створюється лише через {@link #create}, одразу у валідному стані;
 *  - Information Expert: totalCost / dispatch / snapshot — тут, бо тут дані;
 *  - стан закритий (private final / private), сеттерів немає.
 */
public final class Shipment {
    private final int trackingId;
    private final Sender sender;
    private final Cargo cargo;
    private final Quote quote;
    private boolean dispatched;

    private Shipment(int trackingId, Sender sender, Cargo cargo, Quote quote) {
        this.trackingId = trackingId;
        this.sender = sender;
        this.cargo = cargo;
        this.quote = quote;
        this.dispatched = false;
    }

    public static Shipment create(int trackingId, Sender sender, Cargo cargo, Quote quote) {
        if (trackingId <= 0) {
            throw new IllegalArgumentException("Трекінг-номер має бути додатним.");
        }
        return new Shipment(trackingId,
                Objects.requireNonNull(sender, "sender"),
                Objects.requireNonNull(cargo, "cargo"),
                Objects.requireNonNull(quote, "quote"));
    }

    public void dispatch() {
        if (dispatched) {
            throw new IllegalStateException("Відправку TTN-" + trackingId + " уже відправлено.");
        }
        dispatched = true;
    }

    public double totalCost() {
        return quote.total();
    }

    public int trackingId() {
        return trackingId;
    }

    public ShipmentSnapshot snapshot() {
        return new ShipmentSnapshot(trackingId, sender.companyName(), sender.email(),
                quote.deliveryType(), cargo.weightKg(), quote.transportFee(), quote.insuranceFee(),
                totalCost(), dispatched ? "Dispatched" : "Created");
    }
}
