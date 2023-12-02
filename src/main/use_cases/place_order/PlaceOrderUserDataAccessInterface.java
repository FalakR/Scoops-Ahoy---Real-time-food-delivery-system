package use_cases.place_order;

import entities.Location;

public interface PlaceOrderUserDataAccessInterface {
    public void setLocation(Location location);
    public void setOrderId(String orderId);
}
