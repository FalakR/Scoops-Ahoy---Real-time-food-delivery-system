package interface_adapters.login;

import use_cases.log_in.LoginInputData;
import use_cases.log_in.LoginInputBoundary;
import use_cases.sign_up.SignupInputBoundary;
import use_cases.sign_up.SignupInputData;

public class LoginController {

    final LoginInputBoundary loginUseCaseInteractor;
    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }


    public void execute(String email, String password) {
        LoginInputData loginInputData = new LoginInputData(
                email, password);

        loginUseCaseInteractor.execute(loginInputData);
    }
}
