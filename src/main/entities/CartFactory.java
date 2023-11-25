package entities;

import java.util.ArrayList;

public interface CartFactory {

    Cart create(ArrayList<IceCream> items);
}
