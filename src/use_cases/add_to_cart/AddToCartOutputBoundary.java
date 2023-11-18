package use_cases.add_to_cart;

public interface AddToCartOutputBoundary {
    void onItemAddedToCart(AddToCartOutputData outputData);
}
