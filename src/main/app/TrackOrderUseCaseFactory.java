package app;

import interface_adapters.ViewManagerModel;
import interface_adapters.track_order.TrackOrderPresenter;
import interface_adapters.track_order.TrackOrderViewModel;
import use_cases.track_order.*;
import view.TrackOrderView;

public class TrackOrderUseCaseFactory {

    private TrackOrderUseCaseFactory() {};

    public static TrackOrderView create(
            ViewManagerModel viewManagerModel,
            TrackOrderViewModel trackOrderViewModel,
            TrackOrderDataAccessInterface trackOrderDataAccessObject,
            TrackOrderUserDataAccessInterface trackOrderUserDataAccessObject
    ) {
        TrackOrderOutputBoundary trackOrderOutputBoundary = new TrackOrderPresenter(
                viewManagerModel,
                trackOrderViewModel
        );
        TrackOrderInputBoundary trackOrderInteractor = new TrackOrderInteractor(
                trackOrderDataAccessObject,
                trackOrderUserDataAccessObject,
                trackOrderOutputBoundary
        );

        return new TrackOrderView(trackOrderViewModel);
    }


}
