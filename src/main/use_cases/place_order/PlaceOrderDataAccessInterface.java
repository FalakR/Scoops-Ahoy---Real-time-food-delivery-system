package use_cases.place_order;

import entities.Item;
import entities.Location;

import java.util.List;

public interface PlaceOrderDataAccessInterface {

    // returns orderId
    public String publish(List<Item> orderItems, Location userLoc);
}