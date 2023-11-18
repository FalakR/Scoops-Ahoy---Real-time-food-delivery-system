package use_cases.add_to_cart;

import entities.IceCream;

public class AddToCartInputData {
    private final IceCream iceCream;

    public AddToCartInputData(IceCream iceCream) {
        this.iceCream = iceCream;
    }

    public IceCream getIceCream() {
        return iceCream;
    }
}
