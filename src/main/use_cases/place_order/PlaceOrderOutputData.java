package use_cases.place_order;

public class PlaceOrderOutputData {
    private final String orderSummary;
    public PlaceOrderOutputData(String orderSummary) {
        this.orderSummary=orderSummary;
    }
    public String getOrderSummary(){
        return orderSummary;
    }
}
