package use_cases.place_order;

public interface PlaceOrderOutputBoundary {
    void prepareSummaryView(PlaceOrderOutputData order);

    void prepareFailView(String s);
}
