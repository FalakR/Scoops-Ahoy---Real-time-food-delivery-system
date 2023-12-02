package app;

import entities.CommonUserFactory;
import entities.UserFactory;
import interface_adapters.ViewManagerModel;
import interface_adapters.place_order.PlaceOrderController;
import interface_adapters.place_order.PlaceOrderPresenter;
import interface_adapters.place_order.PlaceOrderViewModel;
import interface_adapters.track_order.TrackOrderViewModel;
import use_cases.place_order.*;
import view.PlaceOrderView;

import javax.swing.*;
import java.io.IOException;

public class PlaceOrderUseCaseFactory {
    private PlaceOrderUseCaseFactory(){};
    public static PlaceOrderView create(
            ViewManagerModel viewManagerModel,
            PlaceOrderViewModel placeOrderViewModel,
            TrackOrderViewModel trackViewModel,
            PlaceOrderDataAccessInterface orderDataAccessObject,
            PlaceOrderUserDataAccessInterface userDataAccessObject,
   ){
        try {
            PlaceOrderController placeOrderController = createPlaceOrderUseCase(viewManagerModel, placeOrderViewModel, trackViewModel, orderDataAccessObject);
            return new PlaceOrderView(placeOrderViewModel, placeOrderController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static PlaceOrderController createPlaceOrderUseCase(
            ViewManagerModel viewManagerModel,
            PlaceOrderViewModel placeOrderViewModel,
            TrackOrderViewModel trackViewModel,
            PlaceOrderDataAccessInterface orderDataAccessObject,
            PlaceOrderUserDataAccessInterface userDataAccessObject) {
        PlaceOrderOutputBoundary placeOrderOutputBoundary= new PlaceOrderPresenter(placeOrderViewModel, viewManagerModel, trackViewModel);

        PlaceOrderInputBoundary placeOrderInteractor = new PlaceOrderInteractor(placeOrderOutputBoundary,orderDataAccessObject,userDataAccessObject);
        return new PlaceOrderController(placeOrderInteractor);
    }

}
