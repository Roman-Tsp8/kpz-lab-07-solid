package variant8_logisticsdelivery.domain;

/** Вантаж. Імутабельний; інваріанти перевіряються при створенні. */
public record Cargo(double weightKg, double volumeCubMetres, double declaredValue) {

    public Cargo {
        if (!(weightKg > 0)) {
            throw new DomainException("Вага вантажу має бути більшою за 0.");
        }
        if (!(volumeCubMetres > 0)) {
            throw new DomainException("Об'єм вантажу має бути більшим за 0.");
        }
        if (!(declaredValue >= 0)) {
            throw new DomainException("Заявлена вартість не може бути від'ємною.");
        }
    }
}
