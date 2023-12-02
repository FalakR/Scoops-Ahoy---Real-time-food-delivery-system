package app;

import entities.CommonUserFactory;
import entities.UserFactory;
import interface_adapters.ViewManagerModel;
import interface_adapters.place_order.PlaceOrderController;
import interface_adapters.place_order.PlaceOrderPresenter;
import interface_adapters.place_order.PlaceOrderViewModel;
import interface_adapters.track_order.TrackOrderViewModel;
import use_cases.place_order.*;
import use_cases.track_order.TrackOrderInputBoundary;
import use_cases.track_order.TrackOrderInteractor;
import view.PlaceOrderView;

import javax.swing.*;
import java.io.IOException;

public class PlaceOrderUseCaseFactory {
    private PlaceOrderUseCaseFactory(){};
    public static PlaceOrderView create(
            ViewManagerModel viewManagerModel,
            PlaceOrderViewModel placeOrderViewModel,
            TrackOrderInputBoundary trackOrderInteractor,
            TrackOrderViewModel trackOrderViewModel,
            PlaceOrderDataAccessInterface orderDataAccessObject,
            PlaceOrderUserDataAccessInterface userDataAccessObject
   ){

        PlaceOrderController placeOrderController = createPlaceOrderUseCase(viewManagerModel, placeOrderViewModel, trackOrderInteractor, trackOrderViewModel, orderDataAccessObject, userDataAccessObject);
        PlaceOrderPresenter placeOrderPresenter = new PlaceOrderPresenter(placeOrderViewModel,viewManagerModel, trackOrderViewModel);
            return new PlaceOrderView(placeOrderViewModel, placeOrderController, placeOrderPresenter);

    }

    private static PlaceOrderController createPlaceOrderUseCase(
            ViewManagerModel viewManagerModel,
            PlaceOrderViewModel placeOrderViewModel,
            TrackOrderInputBoundary trackOrderInteractor,
            TrackOrderViewModel trackOrderViewModel,
            PlaceOrderDataAccessInterface orderDataAccessObject,
            PlaceOrderUserDataAccessInterface userDataAccessObject) {
        PlaceOrderOutputBoundary placeOrderOutputBoundary= new PlaceOrderPresenter(
                placeOrderViewModel, viewManagerModel,
                trackOrderViewModel
        );

        PlaceOrderInputBoundary placeOrderInteractor = new PlaceOrderInteractor(
                placeOrderOutputBoundary,orderDataAccessObject,
                userDataAccessObject, trackOrderInteractor
        );
        return new PlaceOrderController(placeOrderInteractor);
    }

}
