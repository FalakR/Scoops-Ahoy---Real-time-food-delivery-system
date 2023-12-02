package interface_adapters.signup;

import interface_adapters.ViewManagerModel;
import interface_adapters.add_to_cart.AddToCartViewModel;
import interface_adapters.login.LoginViewModel;
import use_cases.sign_up.SignupOutputBoundary;

public class SignupPresenter implements SignupOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private final AddToCartViewModel browseViewModel;
    private ViewManagerModel viewManagerModel;

    public SignupPresenter(ViewManagerModel viewManagerModel,
                           SignupViewModel signupViewModel,
                           LoginViewModel loginViewModel,
                           AddToCartViewModel addToCartViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.browseViewModel = addToCartViewModel;
    }

    @Override
    public void prepareSuccessView() {
        // On success, switch to the browse view.

        browseViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(browseViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareLoginView() {
        // On clocking the login button, switch to the Login view.

        loginViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

        SignupState signupState = signupViewModel.getState();
        signupState.setEmailError(error);
        signupViewModel.firePropertyChanged();
    }
}
