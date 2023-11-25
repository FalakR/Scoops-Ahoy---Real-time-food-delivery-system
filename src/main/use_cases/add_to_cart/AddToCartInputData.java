package use_cases.add_to_cart;

import entities.IceCream;

import java.util.ArrayList;

public class AddToCartInputData {

    private final ArrayList<IceCream> iceCreams;

    public AddToCartInputData(ArrayList<IceCream> iceCreams) {
        this.iceCreams = iceCreams;
    }

    ArrayList<IceCream> getIceCreams(){
        return iceCreams;
    }
}
