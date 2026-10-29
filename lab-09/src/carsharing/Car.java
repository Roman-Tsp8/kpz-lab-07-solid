package carsharing;

import java.util.ArrayList;
import java.util.List;

/** Автомобіль парку: дороге введення в роботу, персональні дані клієнта, які скидаються. */
public class Car {
    private final String plate;
    private final String model;
    private String mediaAccount;
    private final List<String> navigatorAddresses = new ArrayList<String>();
    private int seatPosition = 5;
    private int trips;

    public Car(String plate, String model) {
        this.plate = plate;
        this.model = model;
        Logger.expensive("Car", "Введення авто " + plate + " (" + model
                + ") в роботу: реєстрація, телематика, страхування", 200);
    }

    public void personalize(String client, String address, int seat) { // information about current client
        this.mediaAccount = client;
        this.navigatorAddresses.add(address);
        this.seatPosition = seat;
        trips++;
    }

    public void reset() {
        mediaAccount = null;
        navigatorAddresses.clear();
        seatPosition = 5;
    }

    public String getPlate() {
        return plate;
    }

    public String getMediaAccount() {
        return mediaAccount;
    }

    public List<String> getNavigatorAddresses() {
        return navigatorAddresses;
    }

    public int getTrips() {
        return trips;
    }
}
