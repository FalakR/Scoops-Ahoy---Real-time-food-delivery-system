package interface_adapters.add_to_cart;

import interface_adapters.ViewManagerModel;
import interface_adapters.place_order.PlaceOrderViewModel;
import use_cases.add_to_cart.AddToCartOutputBoundary;
import use_cases.add_to_cart.AddToCartOutputData;

public class AddToCartPresenter implements AddToCartOutputBoundary {

    private final AddToCartViewModel addToCartViewModel;

    private final PlaceOrderViewModel placeOrderViewModel;

    private ViewManagerModel viewManagerModel;


    // Constructor

    public AddToCartPresenter(ViewManagerModel viewManagerModel,
                              AddToCartViewModel addToCartViewModel,
                              PlaceOrderViewModel placeOrderViewModel){

        this.viewManagerModel = viewManagerModel;
        this.addToCartViewModel = addToCartViewModel;
        this.placeOrderViewModel = placeOrderViewModel;

    }

    public void prepareSuccessView(AddToCartOutputData cart) {

        viewManagerModel.setActiveView(placeOrderViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


}
