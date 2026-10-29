package variant8_logisticsdelivery;

import variant8_logisticsdelivery.application.*;
import variant8_logisticsdelivery.domain.*;
import variant8_logisticsdelivery.infrastructure.*;
import variant8_logisticsdelivery.ui.ConsoleCommandParser;

import java.util.ArrayList;
import java.util.List;

/** Мінімальний тест-раннер без зовнішніх залежностей: java -cp out variant8_logisticsdelivery.LogisticsTests */
public class LogisticsTests {
    static int passed = 0, failed = 0;

    public static void main(String[] args) {
        test("Standard: 120 кг * 15 = 1800, страховка 250", () -> {
            Quote q = pricing().quote("Standard", new Cargo(120, 1.5, 50000));
            eq(1800.0, q.transportFee()); eq(250.0, q.insuranceFee()); eq(2050.0, q.total());
        });
        test("Express x1.8 = 3240", () -> eq(3240.0, pricing().quote("express", new Cargo(120, 1.5, 50000)).transportFee()));
        test("Freight x1.2 = 2160", () -> eq(2160.0, pricing().quote("FREIGHT", new Cargo(120, 1.5, 50000)).transportFee()));
        test("Невідомий тип -> DomainException", () -> throwsDomain(() -> pricing().quote("Teleport", new Cargo(1, 1, 1))));
        test("Cargo: вага <= 0 відхиляється", () -> throwsDomain(() -> new Cargo(0, 1, 1)));
        test("Cargo: від'ємна вартість відхиляється", () -> throwsDomain(() -> new Cargo(1, 1, -5)));
        test("Sender: порожня компанія відхиляється", () -> throwsDomain(() -> new Sender(1, " ", "A", "a@b.ua")));
        test("Sender: поганий email відхиляється", () -> throwsDomain(() -> new Sender(1, "X", "A", "nope")));
        test("Shipment: подвійний dispatch заборонено", () -> {
            Shipment s = shipment();
            s.dispatch();
            try { s.dispatch(); throw new AssertionError("очікували IllegalStateException"); }
            catch (IllegalStateException expected) { }
        });
        test("Shipment: snapshot до/після dispatch", () -> {
            Shipment s = shipment();
            eq("Created", s.snapshot().status());
            s.dispatch();
            eq("Dispatched", s.snapshot().status());
            eq("TTN-770099", s.snapshot().ttn());
        });
        test("Controller: повний сценарій + слухачі + реєстр", () -> {
            List<String> events = new ArrayList<>();
            var repo = new InMemoryShipmentRepository();
            var controller = new ShipmentController(
                    new ShipmentFactory(pricing(), new SequentialTrackingIdGenerator(770099)),
                    new DispatchService(repo, snap -> events.add(snap.ttn() + "->" + snap.senderEmail())));
            var cmd = new ConsoleCommandParser().parse("Acme", "Express", "", "", "");
            var snap = controller.registerShipment(cmd);
            eq(3490.0, snap.totalCost());
            eq("Dispatched", snap.status());
            eq(1, controller.listShipments().size());
            eq("TTN-770099->logistics@company.ua", events.get(0));
            eq(770100, controller.registerShipment(cmd).trackingId());
        });
        test("Parser: нечислове поле -> IllegalArgumentException", () -> {
            try { new ConsoleCommandParser().parse("A", "Standard", "abc", "", ""); throw new AssertionError(); }
            catch (IllegalArgumentException expected) { }
        });
        System.out.println("\nПройдено: " + passed + ", провалено: " + failed);
        if (failed > 0) System.exit(1);
    }

    static PricingCatalog pricing() {
        return new TariffPricingCatalog(List.of(new StandardTariff(), new ExpressTariff(), new FreightTariff()),
                new PercentageInsurancePolicy(0.005));
    }

    static Shipment shipment() {
        Cargo c = new Cargo(120, 1.5, 50000);
        return Shipment.create(770099, new Sender(55, "Acme", "Іван", "a@b.ua"), c, pricing().quote("Standard", c));
    }

    static void test(String name, Runnable r) {
        try { r.run(); passed++; System.out.println("OK   " + name); }
        catch (Throwable t) { failed++; System.out.println("FAIL " + name + " -> " + t); }
    }
    static void eq(Object exp, Object act) { if (!exp.equals(act)) throw new AssertionError("очікували " + exp + ", отримали " + act); }
    static void throwsDomain(Runnable r) {
        try { r.run(); throw new AssertionError("очікували DomainException"); } catch (DomainException expected) { }
    }
}
