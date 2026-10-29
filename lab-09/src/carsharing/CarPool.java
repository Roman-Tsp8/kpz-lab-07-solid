package carsharing;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Завдання 1 (Object Pool): фіксований парк авто.
 * Стратегія при відсутності вільних авто — FIFO-черга: чесна (першим прийшов — першим отримав),
 * проста, і авто, що звільнилось, одразу передається першому в черзі без простою.
 */
public class CarPool {
    private final Deque<Car> free = new ArrayDeque<Car>();  //вільнs авто
    private final Map<String, Car> inUse = new HashMap<String, Car>(); // клієнт -> авто
    private final Deque<String> waiting = new ArrayDeque<String>(); //черга клієнтів
    private final List<Car> all = new ArrayList<Car>();

    public CarPool(String[][] fleet) {
        for (String[] f : fleet) {
            Car c = new Car(f[0], f[1]);
            all.add(c);
            free.add(c);
        }
    }

    /** Видає авто клієнту або ставить його в чергу (повертає null). */
    public synchronized Car acquire(String client) {
        Car car = free.poll();
        if (car == null) {
            waiting.add(client);
            Logger.log("CarPool", "Вільних авто немає: клієнт " + client
                    + " очікує в черзі (позиція " + waiting.size() + ")");
            return null;
        }
        issue(car, client);
        return car;
    }

    /** Повертає авто в пул, скидає дані й одразу видає наступному в черзі. */
    public synchronized void release(Car car) {
        String owner = null;
        for (Map.Entry<String, Car> e : inUse.entrySet()) {
            if (e.getValue() == car) {
                owner = e.getKey();
            }
        }
        if (owner == null) {
            return;
        }
        inUse.remove(owner);
        car.reset();
        Logger.log("CarPool", car.getPlate() + " повернено, персональні дані стерто");
        String next = waiting.poll();
        if (next != null) {
            issue(car, next);
        } else {
            free.add(car);
        }
    }

    private void issue(Car car, String client) {
        car.personalize(client, "вул. " + client + ", 1", 3);
        inUse.put(client, car);
        Logger.log("CarPool", "Авто " + car.getPlate() + " → клієнт " + client);
    }

    public synchronized void printStats() {
        int total = 0;
        for (Car c : all) {
            Logger.log("CarPool", "Статистика: " + c.getPlate() + " — поїздок: " + c.getTrips());
            total += c.getTrips();
        }
        Logger.log("CarPool", "Усього поїздок: " + total + ", введено в роботу авто: " + all.size());
    }
}
