package entities;

import java.util.ArrayList;

public class CommonCartFactory implements CartFactory{
    public Cart create(ArrayList<IceCream> items) {
        return new CommonCart(items);
    }
}
