package entities;

import java.util.ArrayList;

public class CommonCart implements Cart{

    private final ArrayList<IceCream> items;

    CommonCart(ArrayList<IceCream> items) {
        this.items = items;
    }

    public ArrayList<IceCream> getItems(){
        return items;
    }

    public void addItems(IceCream iceCream){
        items.add(iceCream);
    }
}
