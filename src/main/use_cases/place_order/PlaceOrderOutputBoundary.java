package use_cases.place_order;

public interface PlaceOrderOutputBoundary {
    void prepareSuccessView(PlaceOrderOutputData order);

    void prepareFailView(String s);
}
