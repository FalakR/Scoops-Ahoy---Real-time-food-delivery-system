package interface_adapters.place_order;


import interface_adapters.track_order.TrackOrderState;
import interface_adapters.track_order.TrackOrderViewModel;
import use_cases.place_order.PlaceOrderOutputBoundary;
import interface_adapters.ViewManagerModel;
import use_cases.place_order.PlaceOrderOutputData;

public class PlaceOrderPresenter implements PlaceOrderOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private PlaceOrderViewModel placeOrderViewModel;

    public PlaceOrderPresenter(PlaceOrderViewModel placeOrderViewModel, ViewManagerModel viewManagerModel) {
        this.placeOrderViewModel = placeOrderViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(PlaceOrderOutputData response) {
//        // switch to the tracking view
//        TrackOrderState trackState = trackViewModel.getState();
//
//        //  TODO: make sure Eren has implemented setAddess and set Order in TrackState.java
//        //trackState.setAddress(response.getUserAddress());
//        //trackState.setOrder(response.getOrderSummary());
//
//        this.trackViewModel.setState(trackState);
//        trackViewModel.firePropertyChanged();
//
//        viewManagerModel.setActiveView(trackViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();
        // TODO: DO THIS
    }

    @Override
    public void prepareFailView(String s) {
        PlaceOrderState placeOrderState = placeOrderViewModel.getState();
        placeOrderState.setAddressError(s);
        placeOrderViewModel.firePropertyChanged();
    }
}
