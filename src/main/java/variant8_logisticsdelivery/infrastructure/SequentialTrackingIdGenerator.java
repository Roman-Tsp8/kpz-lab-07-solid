package variant8_logisticsdelivery.infrastructure;

import variant8_logisticsdelivery.application.TrackingIdGenerator;

import java.util.concurrent.atomic.AtomicInteger;

public final class SequentialTrackingIdGenerator implements TrackingIdGenerator {
    private final AtomicInteger next;

    public SequentialTrackingIdGenerator(int start) {
        this.next = new AtomicInteger(start);
    }

    @Override
    public int nextId() {
        return next.getAndIncrement();
    }
}
