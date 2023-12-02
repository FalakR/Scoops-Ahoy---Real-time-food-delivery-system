package interface_adapters.place_order;


import interface_adapters.track_order.TrackOrderState;
import interface_adapters.track_order.TrackOrderViewModel;
import use_cases.place_order.PlaceOrderOutputBoundary;
import interface_adapters.ViewManagerModel;

public class PlaceOrderPresenter implements PlaceOrderOutputBoundary {

    private final TrackOrderViewModel trackViewModel;
    private ViewManagerModel viewManagerModel;

    public PlaceOrderPresenter(TrackOrderViewModel trackViewModel, ViewManagerModel viewManagerModel) {
        this.trackViewModel = trackViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(String s) {
        // switch to the tracking view
        TrackOrderState trackState = trackViewModel.getState();

        this.trackViewModel.setState(trackState);
        trackViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(trackViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String s) {

    }
}
