package use_cases.place_order;

public interface PlaceOrderOutputBoundary {
    void prepareSuccessView(String s);

    void prepareFailView(String s);
}
