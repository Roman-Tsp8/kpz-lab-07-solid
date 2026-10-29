package variant8_logisticsdelivery.ui;

import variant8_logisticsdelivery.application.ShipmentController;

/** Інтерактивне консольне меню. Читає команди, викликає контролер, друкує результат. */
public final class ConsoleApp {
    private final ConsoleShipmentView view;
    private final ConsoleCommandParser parser;
    private final WaybillFormatter formatter;
    private final ShipmentController controller;

    public ConsoleApp(ConsoleShipmentView view, ConsoleCommandParser parser,
                      WaybillFormatter formatter, ShipmentController controller) {
        this.view = view;
        this.parser = parser;
        this.formatter = formatter;
        this.controller = controller;
    }

    public void run() {
        view.show("=== Логістична компанія ===");
        while (true) {
            view.show("\n1 - Оформити відправку\n2 - Реєстр відправок\n0 - Вихід");
            String choice = view.ask("Ваш вибір: ");
            if (choice == null || "0".equals(choice.trim())) {
                view.show("До побачення.");
                return;
            }
            switch (choice.trim()) {
                case "1" -> createShipment();
                case "2" -> showRegistry();
                default -> view.showError("Невідома команда: " + choice);
            }
        }
    }

    private void createShipment() {
        try {
            var command = parser.parse(
                    view.ask("Назва компанії-відправника: "),
                    view.ask("Тип доставки (Standard/Express/Freight) [Standard]: "),
                    view.ask("Вага, кг [120]: "),
                    view.ask("Об'єм, м3 [1.5]: "),
                    view.ask("Заявлена вартість, грн [50000]: "));
            view.show(formatter.waybill(controller.registerShipment(command)));
        } catch (IllegalArgumentException | IllegalStateException e) {
            view.showError(e.getMessage());
        }
    }

    private void showRegistry() {
        var all = controller.listShipments();
        if (all.isEmpty()) {
            view.show("Реєстр порожній.");
            return;
        }
        all.forEach(s -> view.show(formatter.registryLine(s)));
    }
}
