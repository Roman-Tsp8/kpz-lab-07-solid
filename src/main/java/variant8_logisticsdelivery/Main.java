package variant8_logisticsdelivery;

import variant8_logisticsdelivery.application.DispatchService;
import variant8_logisticsdelivery.application.ShipmentController;
import variant8_logisticsdelivery.application.ShipmentFactory;
import variant8_logisticsdelivery.domain.ExpressTariff;
import variant8_logisticsdelivery.domain.FreightTariff;
import variant8_logisticsdelivery.domain.PercentageInsurancePolicy;
import variant8_logisticsdelivery.domain.StandardTariff;
import variant8_logisticsdelivery.domain.TariffPricingCatalog;
import variant8_logisticsdelivery.infrastructure.CompositeDispatchListener;
import variant8_logisticsdelivery.infrastructure.EmailDispatchListener;
import variant8_logisticsdelivery.infrastructure.InMemoryShipmentRepository;
import variant8_logisticsdelivery.infrastructure.RegistryLogListener;
import variant8_logisticsdelivery.infrastructure.SequentialTrackingIdGenerator;
import variant8_logisticsdelivery.ui.ConsoleApp;
import variant8_logisticsdelivery.ui.ConsoleCommandParser;
import variant8_logisticsdelivery.ui.ConsoleShipmentView;
import variant8_logisticsdelivery.ui.WaybillFormatter;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

/** Composition Root: єдине місце, що знає всі конкретні класи й збирає граф залежностей. */
public final class Main {
    public static void main(String[] args) {
        PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        Scanner in = new Scanner(System.in, StandardCharsets.UTF_8);

        var pricing = new TariffPricingCatalog(
                List.of(new StandardTariff(), new ExpressTariff(), new FreightTariff()),
                new PercentageInsurancePolicy(0.005));
        var listener = new CompositeDispatchListener(List.of(
                new RegistryLogListener(out), new EmailDispatchListener(out)));
        var controller = new ShipmentController(
                new ShipmentFactory(pricing, new SequentialTrackingIdGenerator(770099)),
                new DispatchService(new InMemoryShipmentRepository(), listener));

        new ConsoleApp(new ConsoleShipmentView(in, out), new ConsoleCommandParser(),
                new WaybillFormatter(), controller).run();
    }
}
