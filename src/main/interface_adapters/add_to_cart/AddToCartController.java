package interface_adapters.add_to_cart;

import entities.IceCream;
import use_cases.add_to_cart.AddToCartInputBoundary;
import use_cases.add_to_cart.AddToCartInputData;

import java.util.ArrayList;

public class AddToCartController {

    final AddToCartInputBoundary addToCartUseCaseInteractor;

    public AddToCartController(AddToCartInputBoundary addToCartUseCaseInteractor) {

        this.addToCartUseCaseInteractor = addToCartUseCaseInteractor;
    }

    public void execute(ArrayList<IceCream> iceCreams) {

        AddToCartInputData addToCartInputData = new AddToCartInputData(iceCreams);
        addToCartUseCaseInteractor.execute(addToCartInputData);

    }

}
