package use_cases.add_to_cart;

import entities.Cart;
import entities.CartFactory;

public class AddToCartInteractor implements AddToCartInputBoundary{

    final AddToCartDataAccessInterface addToCartDataAccessObject;
    final AddToCartOutputBoundary addToCartPresenter;
    final CartFactory cartFactory;

    public AddToCartInteractor(AddToCartDataAccessInterface addToCartDataAccessObject, AddToCartOutputBoundary addToCartOutputBoundary, CartFactory cartFactory) {

        this.addToCartDataAccessObject = addToCartDataAccessObject;
        this.addToCartPresenter = addToCartOutputBoundary;
        this.cartFactory = cartFactory;
    }

    @Override
    public void execute(AddToCartInputData addToCartInputData) {

        Cart cart = cartFactory.create(addToCartInputData.getIceCreams());
        AddToCartOutputData addToCartOutputData = new AddToCartOutputData(cart);
        addToCartPresenter.prepareSuccessView(addToCartOutputData);
    }
}
