package app;

import entities.CommonUserFactory;
import entities.UserFactory;
import interface_adapters.ViewManagerModel;
import interface_adapters.login.LoginController;
import interface_adapters.place_order.PlaceOrderController;
import interface_adapters.place_order.PlaceOrderPresenter;
import interface_adapters.place_order.PlaceOrderViewModel;
import interface_adapters.track.TrackViewModel;
import use_cases.place_order.PlaceOrderDataAccessInterface;
import use_cases.place_order.PlaceOrderInputBoundary;
import use_cases.place_order.PlaceOrderInteractor;
import use_cases.place_order.PlaceOrderOutputBoundary;
import view.LoginView;
import view.PlaceOrderView;

import javax.swing.*;
import java.io.IOException;

public class PlaceOrderUseCaseFactory {
    private PlaceOrderUseCaseFactory(){};
    public static PlaceOrderView create(
            ViewManagerModel viewManagerModel,
            PlaceOrderViewModel placeOrderViewModel,
            TrackViewModel trackViewModel,
            PlaceOrderDataAccessInterface orderDataAccessObject
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
            TrackViewModel trackViewModel,
            PlaceOrderDataAccessInterface orderDataAccessObject) {
        PlaceOrderOutputBoundary placeOrderOutputBoundary= new PlaceOrderPresenter(placeOrderViewModel, trackViewModel, viewManagerModel);
        UserFactory userFactory = new CommonUserFactory();
        PlaceOrderInputBoundary placeOrderInteractor = new PlaceOrderInteractor(placeOrderOutputBoundary);
        return new PlaceOrderController(placeOrderInteractor);
    }

}
