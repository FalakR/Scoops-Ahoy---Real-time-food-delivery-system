package use_cases.place_order;

public class PlaceOrderOutputData {
    private final String orderSummary;
    private final String userAddress;
    public PlaceOrderOutputData(String orderSummary, String userAddress) {
        this.orderSummary=orderSummary;
        this.userAddress= userAddress;
    }
    public String getOrderSummary(){
        return orderSummary;
    }
    public String getUserAddress(){
        return userAddress;
    }
}
