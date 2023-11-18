package entities;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<IceCream> items = new ArrayList<>();

    public void addItem(IceCream iceCream) {
        items.add(iceCream);
    }

    // Other cart-related methods
}
