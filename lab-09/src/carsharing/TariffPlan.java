package carsharing;

import java.util.ArrayList;
import java.util.List;

/** Тарифний план — Prototype з глибоким копіюванням вкладених даних. */
public class TariffPlan implements Prototype<TariffPlan> {

    /** Вкладений об'єкт: страхування. */
    public static class Insurance {
        private String type;
        private int deductible;

        public Insurance(String type, int deductible) {
            this.type = type;
            this.deductible = deductible;
        }

        public Insurance(Insurance other) {
            this.type = other.type;
            this.deductible = other.deductible;
        }

        public void set(String type, int deductible) {
            this.type = type;
            this.deductible = deductible;
        }

        @Override
        public String toString() {
            return type + " (франшиза " + deductible + " грн)";
        }
    }

    private String name;
    private double pricePerMinute;
    private double pricePerKm;
    private int speedLimit;
    private Insurance insurance;
    private final List<String> endZones = new ArrayList<String>();
    private final List<String> bonuses = new ArrayList<String>();

    /** Дороге налаштування з нуля. */
    public TariffPlan(String name, double pricePerMinute, double pricePerKm, int speedLimit) {
        Logger.expensive("TariffPlan", "Налаштування тарифу «" + name + "» з нуля", 500);
        this.name = name;
        this.pricePerMinute = pricePerMinute;
        this.pricePerKm = pricePerKm;
        this.speedLimit = speedLimit;
        this.insurance = new Insurance("базове", 5000);
        this.endZones.add("Центр");
        this.endZones.add("Рогізна");
        this.bonuses.add("кешбек 5%");
    }

    /** Копіювальний конструктор — без дорогого налаштування. */
    private TariffPlan(TariffPlan o) {
        this.name = o.name;
        this.pricePerMinute = o.pricePerMinute;
        this.pricePerKm = o.pricePerKm;
        this.speedLimit = o.speedLimit;
        this.insurance = new Insurance(o.insurance);
        this.endZones.addAll(o.endZones);   // String незмінний — копії списку достатньо
        this.bonuses.addAll(o.bonuses);
    }

    @Override
    public TariffPlan copy() {
        Logger.log("TariffPlan", "Клонування тарифу «" + name + "» (без повторного налаштування)");
        return new TariffPlan(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPricePerMinute(double p) {
        this.pricePerMinute = p;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public List<String> getEndZones() {
        return endZones;
    }

    public List<String> getBonuses() {
        return bonuses;
    }

    @Override
    public String toString() {
        return String.format("«%s»: %.2f грн/хв, %.2f грн/км, ліміт %d км/год, страхування: %s, зони: %s, бонуси: %s",
                name, pricePerMinute, pricePerKm, speedLimit, insurance, endZones, bonuses);
    }
}
