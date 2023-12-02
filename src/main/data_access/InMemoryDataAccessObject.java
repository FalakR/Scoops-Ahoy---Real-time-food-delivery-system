package data_access;

import entities.Location;
import use_cases.place_order.PlaceOrderUserDataAccessInterface;
import use_cases.track_order.TrackOrderUserDataAccessInterface;

public class InMemoryDataAccessObject implements TrackOrderUserDataAccessInterface, PlaceOrderUserDataAccessInterface {
    private String orderId;
    private Location userLocation;

    @Override
    public String getOrderId() {
        return this.orderId;
    }

    @Override
    public Location getUserLocation() {
        return this.userLocation;
    }

    @Override
    public void setLocation(Location location) {
        this.userLocation = location;
    }

    @Override
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
