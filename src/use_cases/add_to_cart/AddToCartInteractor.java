package use_cases.add_to_cart;

import entities.Cart;
import entities.IceCream;

public class AddToCartInteractor implements AddToCartInputBoundary {
    private final Cart cart;
    private final AddToCartOutputBoundary outputBoundary;

    public AddToCartInteractor(Cart cart, AddToCartOutputBoundary outputBoundary) {
        this.cart = cart;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void addToCart(AddToCartInputData inputData) {
        IceCream iceCream = inputData.getIceCream();
        cart.addItem(iceCream);
        outputBoundary.onItemAddedToCart(new AddToCartOutputData());
    }
}