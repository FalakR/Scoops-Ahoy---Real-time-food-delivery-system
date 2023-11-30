package entities;

import java.util.ArrayList;

public interface Cart {
    ArrayList<IceCream> getItems();

    void addItems(IceCream iceCream);
}
