package use_cases.log_in;

public interface LoginOutputBoundary {
    void prepareSuccessView();
    void prepareFailViewEmpty(String error);

    void prepareFailViewEmail(String error);
    void prepareFailViewPassword(String error);
}