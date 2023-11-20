package use_cases.sign_up;

public interface SignupOutputBoundary {
    void prepareSuccessView();

    void prepareLoginView();

    void prepareFailView(String error);
}
