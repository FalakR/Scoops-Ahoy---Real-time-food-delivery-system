package use_cases.add_to_cart;

import entities.IceCream;

public interface AddToCartDataAccessInterface {

    IceCream get(String name);
}
