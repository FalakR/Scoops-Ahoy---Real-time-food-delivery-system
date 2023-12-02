package use_cases.place_order;

public interface PlaceOrderOutputBoundary {
    void prepareSuccessView(PlaceOrderOutputData order);
    void prepareChangeView();

    void prepareFailView(String s);
}
