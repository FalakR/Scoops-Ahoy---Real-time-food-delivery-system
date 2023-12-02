package app;

import data_access.FileIceCreamDataAccessObject;
import entities.CartFactory;
import entities.CommonCartFactory;
import interface_adapters.ViewManagerModel;
import interface_adapters.add_to_cart.AddToCartController;
import interface_adapters.add_to_cart.AddToCartPresenter;
import interface_adapters.add_to_cart.AddToCartViewModel;
import interface_adapters.place_order.PlaceOrderViewModel;
import use_cases.add_to_cart.AddToCartDataAccessInterface;
import use_cases.add_to_cart.AddToCartInputBoundary;
import use_cases.add_to_cart.AddToCartInteractor;
import use_cases.add_to_cart.AddToCartOutputBoundary;
import view.BrowseView;
import view.PlaceOrderView;

public class AddToCartUseCaseFactory {

    private AddToCartUseCaseFactory() {};

    public static BrowseView create(ViewManagerModel viewManagerModel, AddToCartViewModel addToCartViewModel, PlaceOrderViewModel placeOrderViewModel, FileIceCreamDataAccessObject fileIceCreamDataAccessObject) {


        AddToCartController addToCartController = createAddToCartController(viewManagerModel, addToCartViewModel, placeOrderViewModel, fileIceCreamDataAccessObject);

        AddToCartPresenter addToCartPresenter = new AddToCartPresenter(viewManagerModel, addToCartViewModel, placeOrderViewModel);

        return new BrowseView(addToCartController, addToCartPresenter, addToCartViewModel, fileIceCreamDataAccessObject);


    }

    private static AddToCartController createAddToCartController(ViewManagerModel viewManagerModel, AddToCartViewModel addToCartViewModel, PlaceOrderViewModel placeOrderViewModel, AddToCartDataAccessInterface addToCartDataAccessInterface){


        AddToCartOutputBoundary addToCartOutputBoundary = new AddToCartPresenter(viewManagerModel, addToCartViewModel, placeOrderViewModel);

        CartFactory cartFactory1 = new CommonCartFactory();

        AddToCartInputBoundary addToCartUseCaseInteractor = new AddToCartInteractor(addToCartDataAccessInterface, addToCartOutputBoundary, cartFactory1);

        return new AddToCartController(addToCartUseCaseInteractor);
    }





}
