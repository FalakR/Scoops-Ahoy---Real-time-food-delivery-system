package use_cases.place_order;

import interface_adapters.place_order.PlaceOrderPresenter;

public class PlaceOrderInteractor implements PlaceOrderInputBoundary{

    final PlaceOrderOutputBoundary placeOrderPresenter;

    public PlaceOrderInteractor(PlaceOrderOutputBoundary placeOrderPresenter) {
        this.placeOrderPresenter = placeOrderPresenter;
    }


//    @Override
//    public void execute(PlaceOrderInputData placeOrderInputData) {
//        PlaceOrderOutputData placeOrderOutputData = new
//    }

}

