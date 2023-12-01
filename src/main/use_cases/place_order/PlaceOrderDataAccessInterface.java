package use_cases.place_order;

import entities.IceCream;
import entities.Location;

import java.util.List;

public interface PlaceOrderDataAccessInterface {

    // returns orderId
    public String publish(List<IceCream> orderItems, Location userLoc);
}