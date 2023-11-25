package use_cases.add_to_cart;

import entities.Cart;

public class AddToCartOutputData {

    private final Cart cart;

    public AddToCartOutputData(Cart cart) {
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }

}