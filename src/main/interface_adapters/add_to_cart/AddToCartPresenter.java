package interface_adapters.add_to_cart;

import interface_adapters.ViewManagerModel;
import interface_adapters.place_order.PlaceOrderViewModel;
import use_cases.add_to_cart.AddToCartOutputBoundary;
import use_cases.add_to_cart.AddToCartOutputData;

public class AddToCartPresenter implements AddToCartOutputBoundary {

    // TODO: Make sure AddToCartViewModel is created properly and the below line is correct.
    private final AddToCartViewModel addToCartViewModel;

    // TODO: Make sure gurveen has implemented PlaceOrderViewModel correctly with is exact name and below line is correct.
    private final PlaceOrderViewModel placeOrderViewModel;

    private ViewManagerModel viewManagerModel;


    // Constructor
    // TODO: Make sure the constructor line is correct after implementing AddToCartViewModel correctly and gurveen's part.
    public AddToCartPresenter(ViewManagerModel viewManagerModel,
                              AddToCartViewModel addToCartViewModel,
                              PlaceOrderViewModel placeOrderViewModel){

        this.viewManagerModel = viewManagerModel;
        this.addToCartViewModel = addToCartViewModel;
        this.placeOrderViewModel = placeOrderViewModel;

    }

    public void prepareSuccessView(AddToCartOutputData cart) {

        // TODO: Make sure the below line is correct after gurveen has implemented the getViewName method correctly.
        viewManagerModel.setActiveView(placeOrderViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


}
