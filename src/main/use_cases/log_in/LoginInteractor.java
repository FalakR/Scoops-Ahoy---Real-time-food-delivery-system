package use_cases.log_in;

import entities.User;

public class LoginInteractor implements LoginInputBoundary {
    final LoginDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        String email = loginInputData.getEmail();
        String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByEmail(email)) {
            loginPresenter.prepareFailViewEmail(email + "Account does not exist.");
        } else {
            String pwd = userDataAccessObject.get(email).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailViewPassword("Incorrect password for " + email + ".");
            } else {

                User user = userDataAccessObject.get(loginInputData.getEmail());

                LoginOutputData loginOutputData = new LoginOutputData(user.getEmail(), false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}