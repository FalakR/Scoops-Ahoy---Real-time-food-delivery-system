package interface_adapters.signup;

import use_cases.sign_up.SignupInputBoundary;
import use_cases.sign_up.SignupInputData;

public class SignupController {

    final SignupInputBoundary userSignupUseCaseInteractor;
    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    public void execute(String name, String email, String password) {
        SignupInputData signupInputData = new SignupInputData(
                name, email, password);

        userSignupUseCaseInteractor.execute(signupInputData);
    }
}
