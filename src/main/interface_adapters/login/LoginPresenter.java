package interface_adapters.login;

import interface_adapters.ViewManagerModel;
import interface_adapters.add_to_cart.AddToCartViewModel;
import use_cases.log_in.LoginOutputBoundary;
import use_cases.log_in.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final AddToCartViewModel browseViewModel;
    private ViewManagerModel viewManagerModel;


    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoginViewModel loginViewModel,
                          AddToCartViewModel addToCartViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.browseViewModel = addToCartViewModel;
    }

    @Override
    public void prepareSuccessView() {
        // On success, switch to the logged in view.

//        LoginState loginState = loginViewModel.getState();
//        loginState.setEmail(response.getEmail());
//        this.loginViewModel.setState(loginState);
//        this.loginViewModel.firePropertyChanged();
//
//        this.viewManagerModel.setActiveView(loginViewModel.getViewName());
//        this.viewManagerModel.firePropertyChanged();

        browseViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(browseViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailViewEmail(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setEmailError(error);
        loginViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailViewPassword(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setPasswordError(error);
        loginViewModel.firePropertyChanged();
    }
}