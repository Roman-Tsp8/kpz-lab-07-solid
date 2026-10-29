package variant8_logisticsdelivery.application;

/** Pure Fabrication: генерація трекінг-номерів — технічна задача, не домен. */
public interface TrackingIdGenerator {
    int nextId();
}
