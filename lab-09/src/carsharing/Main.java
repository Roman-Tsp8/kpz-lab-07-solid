package carsharing;

import java.util.ArrayList;
import java.util.List;

/** Раннер: окремий метод для кожної ситуації. */
public class Main {

    public static void main(String[] args) throws Exception {
        // Щоб українські літери коректно виводилися в будь-якій консолі
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        scenario1ObjectPool();
        scenario2Prototype();
        scenario3Singleton();
    }

    /** Завдання 1: видача автомобілів парку (Object Pool). */
    static void scenario1ObjectPool() {
        Logger.section("Завдання 1. Видача автомобілів (Object Pool)");
        CarPool pool = new CarPool(new String[][]{
                {"CE1234AA", "Renault Zoe"}, {"CE5678BB", "Nissan Leaf"},
                {"CE9012CC", "Kia Niro"}, {"CE3456DD", "Skoda Fabia"}});
        String[] clients = {"Ткачук", "Бойко", "Мельник", "Коваль", "Шевченко", "Кравчук"};
        List<Car> got = new ArrayList<Car>();
        for (String c : clients) {
            Logger.log("Застосунок", "Клієнт " + c + " бронює авто");
            got.add(pool.acquire(c));
        }
        Car first = got.get(0);
        Logger.log("Застосунок", "Ткачук завершує поїздку");
        pool.release(first);
        Logger.log("Перевірка", "Акаунт мультимедіа в " + first.getPlate() + ": " + first.getMediaAccount()
                + " (очікуємо Шевченко — новий клієнт; акаунта Ткачука немає)");
        Logger.log("Перевірка", "Адреси навігатора: " + first.getNavigatorAddresses());
        Car second = got.get(1);
        Logger.log("Застосунок", "Бойко завершує поїздку");
        pool.release(second);
        pool.printStats();
    }

    /** Завдання 2: корпоративні тарифи (Prototype). */
    static void scenario2Prototype() {
        Logger.section("Завдання 2. Корпоративні тарифи (Prototype)");
        TariffPlan standard = new TariffPlan("Стандартний", 3.50, 8.00, 90);
        String[] companies = {"ТОВ Буковина-Трейд", "ТОВ Карпати-Сервіс", "ТОВ Прут-Логістик"};
        List<TariffPlan> corp = new ArrayList<TariffPlan>();
        for (String c : companies) {
            TariffPlan t = standard.copy();
            t.setName("Корпоративний " + c);
            corp.add(t);
        }
        TariffPlan changed = corp.get(0);
        changed.getInsurance().set("повне", 0);
        changed.getEndZones().add("Аеропорт");
        changed.setPricePerMinute(2.90);
        Logger.log("Раннер", "Стандартний: " + standard);
        Logger.log("Раннер", "Змінений корпоративний: " + changed);
        Logger.log("Раннер", "Інший корпоративний: " + corp.get(1));
    }

    /** Завдання 3: карта парку (Singleton). */
    static void scenario3Singleton() {
        Logger.section("Завдання 3. Карта парку (Singleton)");
        FleetMap dispatcherMap = FleetMap.getInstance();
        dispatcherMap.reserve("CE1234AA", "Диспетчер", "корпоративний клієнт");
        FleetMap appMap = FleetMap.getInstance();
        appMap.reserve("CE1234AA", "Застосунок", "приватний клієнт");
        Logger.log("Диспетчер", "id карти: " + dispatcherMap.id());
        Logger.log("Застосунок", "id карти: " + appMap.id());
        Logger.log("Раннер", "Той самий екземпляр: " + (dispatcherMap == appMap));
    }
}
