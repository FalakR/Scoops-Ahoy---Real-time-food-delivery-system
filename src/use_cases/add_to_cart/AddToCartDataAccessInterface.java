package use_cases.add_to_cart;

import entities.Cart;
import entities.IceCream;

public interface AddToCartDataAccessInterface {
    void addToCart(IceCream iceCream);
    Cart getCart();
}