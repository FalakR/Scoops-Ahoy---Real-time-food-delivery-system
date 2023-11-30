package interface_adapters.place_order;


import interface_adapters.track.TrackState;
import interface_adapters.track.TrackViewModel;
import use_cases.place_order.PlaceOrderOutputBoundary;
import interface_adapters.ViewManagerModel;
import use_cases.place_order.PlaceOrderOutputData;

public class PlaceOrderPresenter implements PlaceOrderOutputBoundary {

    private final PlaceOrderViewModel placeOrderViewModel;

    private final TrackViewModel trackViewModel;
    private ViewManagerModel viewManagerModel;

    public PlaceOrderPresenter(PlaceOrderViewModel placeOrderViewModel, TrackViewModel trackViewModel, ViewManagerModel viewManagerModel) {
        this.placeOrderViewModel = placeOrderViewModel;
        this.trackViewModel = trackViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(PlaceOrderOutputData response) {
        // switch to the tracking view
        TrackState trackState = trackViewModel.getState();
        //trackState.setOrder(response.getOrderSummary());

        this.trackViewModel.setState(trackState);
        trackViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(trackViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String s) {
        PlaceOrderState placeOrderState = placeOrderViewModel.getState();
        placeOrderState.setAddressError(s);
        placeOrderViewModel.firePropertyChanged();
    }
}
