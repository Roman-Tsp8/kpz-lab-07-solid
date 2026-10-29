package variant8_logisticsdelivery.domain;

/** Спільна база: вартість = вага * базова ставка * коефіцієнт типу доставки. */
public abstract class WeightBasedTariff implements TransportTariff {
    public static final double BASE_RATE_PER_KG = 15.0;

    private final String name;
    private final double multiplier;

    protected WeightBasedTariff(String name, double multiplier) {
        this.name = name;
        this.multiplier = multiplier;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public double transportFee(Cargo cargo) {
        return cargo.weightKg() * BASE_RATE_PER_KG * multiplier;
    }
}
