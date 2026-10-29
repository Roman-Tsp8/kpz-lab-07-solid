package variant8_logisticsdelivery.domain;

/** Страховка = відсоток від заявленої вартості (у legacy — 0.5%). */
public final class PercentageInsurancePolicy implements InsurancePolicy {
    private final double rate;

    public PercentageInsurancePolicy(double rate) {
        if (rate < 0) {
            throw new DomainException("Страховий відсоток не може бути від'ємним.");
        }
        this.rate = rate;
    }

    @Override
    public double premium(Cargo cargo) {
        return cargo.declaredValue() * rate;
    }
}
