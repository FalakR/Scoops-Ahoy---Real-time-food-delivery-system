package use_cases.place_order;

import entities.IceCream;

import java.util.ArrayList;
import java.util.List;

public class PlaceOrderInteractor implements PlaceOrderInputBoundary {

    final PlaceOrderOutputBoundary placeOrderPresenter;

    public PlaceOrderInteractor(PlaceOrderOutputBoundary placeOrderPresenter) {
        this.placeOrderPresenter = placeOrderPresenter;
    }

    @Override
    public void execute(PlaceOrderInputData placeOrderInputData) {
        // Extract necessary information from the input data
        List<IceCream> iceCreams = placeOrderInputData.getIceCreams();// list of icecream objects
        String userAddress = placeOrderInputData.getUserAddress();
        String creditCardNumber = placeOrderInputData.getCreditCardNumber();
        int cvv = placeOrderInputData.getCvv();
        String expiryDate = placeOrderInputData.getExpiryDate();

        // Example validation, replace with your actual validation logic
        if (userAddress == null || userAddress.isEmpty()) {
            placeOrderPresenter.prepareFailView("User address cannot be empty.");
        } else if (creditCardNumber == null || creditCardNumber.isEmpty() || cvv <= 0 || expiryDate == null || expiryDate.isEmpty()) {
            placeOrderPresenter.prepareFailView("Invalid credit card information.");
        } else {
            // Process the order
            String orderSummary = createOrderSummary(iceCreams, userAddress);
            PlaceOrderOutputData placeOrderOutputData = new PlaceOrderOutputData(orderSummary, userAddress);
            placeOrderPresenter.prepareSuccessView(placeOrderOutputData);

            // Additional logic based on the result
//            if (orderSummary != null) {
//                // Order placed successfully, you can perform additional actions here
//                placeOrderPresenter.prepareSuccessView("Order Summary: " + orderSummary);
//            } else {
//                // Order failed, handle accordingly
//                placeOrderPresenter.prepareFailView("Order failed. Please try again.");
//            }
        }
    }

    private String createOrderSummary(List<IceCream> iceCreams, String userAddress) {
        if (iceCreams.isEmpty()) {
            return "No items in the order.";
        }
        StringBuilder orderSummaryBuilder = new StringBuilder("Order Summary:\n");

        double totalPrice = 0.0;

        for (int i = 0; i < iceCreams.size(); i++) {
            IceCream iceCream = iceCreams.get(i);
            orderSummaryBuilder.append(i + 1)
                    .append(". ")
                    .append("Name: ").append(iceCream.getName())
                    .append(", Flavor: ").append(iceCream.getFlavour())
                    .append(", Price: $").append(iceCream.getPrice())
                    .append("\n");

            totalPrice += iceCream.getPrice();
        }
        // Add user address to the summary
        orderSummaryBuilder.append("User Address: ").append(userAddress).append("\n");

        // Add total price to the summary
        orderSummaryBuilder.append("Total Price: $").append(totalPrice);

        return orderSummaryBuilder.toString();

    }


//    private List<String> listIceCreamNames(List<IceCream> iceCreams) {
//        List<String> icecreams =  new ArrayList<>();
//        for(IceCream i: iceCreams){
//            icecreams.add(i.getName());
//        }
//        return icecreams;
//    }


}