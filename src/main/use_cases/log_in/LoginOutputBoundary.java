package use_cases.log_in;

public interface LoginOutputBoundary {
    void prepareSuccessView(LoginOutputData user);

    void prepareFailViewEmail(String error);
    void prepareFailViewPassword(String error);
}