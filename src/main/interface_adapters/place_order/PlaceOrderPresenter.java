package interface_adapters.place_order;


import interface_adapters.track.TrackState;
import interface_adapters.track.TrackViewModel;
import use_cases.place_order.PlaceOrderOutputBoundary;
import interface_adapters.ViewManagerModel;

public class PlaceOrderPresenter implements PlaceOrderOutputBoundary {

    private final TrackViewModel trackViewModel;
    private ViewManagerModel viewManagerModel;

    public PlaceOrderPresenter(TrackViewModel trackViewModel, ViewManagerModel viewManagerModel) {
        this.trackViewModel = trackViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(String s) {
        // switch to the tracking view
        TrackState trackState = trackViewModel.getState();

        this.trackViewModel.setState(trackState);
        trackViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(trackViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String s) {

    }
}
