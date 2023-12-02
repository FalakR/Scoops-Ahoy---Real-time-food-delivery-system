package interface_adapters.place_order;

import entities.CommonCart;
import entities.IceCream;
import use_cases.place_order.PlaceOrderInputBoundary;
import use_cases.place_order.PlaceOrderInputData;

import java.util.List;

public class PlaceOrderController {
    final PlaceOrderInputBoundary placeOrderUseCaseInteractor;

    public PlaceOrderController(PlaceOrderInputBoundary placeOrderUseCaseInteractor) {
        this.placeOrderUseCaseInteractor = placeOrderUseCaseInteractor;
    }

    public void execute(CommonCart cart, List<IceCream> iceCreams, String userAddress, String creditCardNumber, int cvv, String expiryDate){
        PlaceOrderInputData placeOrderInputData= new PlaceOrderInputData(cart,  userAddress, creditCardNumber, cvv, expiryDate);

        placeOrderUseCaseInteractor.execute(placeOrderInputData);
    }

}
