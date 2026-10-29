package carsharing;

import java.util.HashMap;
import java.util.Map;

/**
 * Завдання 3 (Singleton): єдина карта парку автомобілів.
 * Диспетчери й мобільний застосунок працюють з тим самим екземпляром.
 * Lazy holder: потокобезпечно, без синхронізації на кожен виклик.
 */
public final class FleetMap {

    private final Map<String, String> reservations = new HashMap<String, String>();
    private final Map<String, String> locations = new HashMap<String, String>();

    private FleetMap() {
        Logger.expensive("FleetMap", "Завантаження карти міста й позицій авто", 300);
        locations.put("CE1234AA", "пл. Центральна");
        locations.put("CE5678BB", "вул. Головна");
        locations.put("CE9012CC", "Рогізна");
        locations.put("CE3456DD", "Садгора");
        Logger.log("FleetMap", "Карту парку ініціалізовано");
    }

    private static final class Holder {
        private static final FleetMap INSTANCE = new FleetMap();
    }

    public static FleetMap getInstance() {
        return Holder.INSTANCE;
    }

    /** Резервує авто за клієнтом. Повертає false, якщо авто вже зарезервоване. */
    public synchronized boolean reserve(String plate, String who, String client) {
        if (!locations.containsKey(plate)) {
            Logger.log(who, "Авто " + plate + " невідоме");
            return false;
        }
        if (reservations.containsKey(plate)) {
            Logger.log(who, "Відмова: Авто " + plate + " зарезервоване (" + reservations.get(plate) + ")");
            return false;
        }
        reservations.put(plate, client);
        Logger.log(who, "Авто " + plate + " зарезервовано для " + client + " (" + locations.get(plate) + ")");
        return true;
    }

    public synchronized String getLocation(String plate) {
        return locations.get(plate);
    }

    public String id() {
        return "FleetMap@" + Integer.toHexString(System.identityHashCode(this));
    }
}
