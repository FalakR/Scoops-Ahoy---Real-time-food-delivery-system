package interface_adapters.signup;

import interface_adapters.ViewManagerModel;
import interface_adapters.browse.BrowseViewModel;
import interface_adapters.login.LoginState;
import interface_adapters.login.LoginViewModel;
import use_cases.sign_up.SignupOutputBoundary;
import use_cases.sign_up.SignupOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SignupPresenter implements SignupOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private final BrowseViewModel browseViewModel;
    private ViewManagerModel viewManagerModel;

    public SignupPresenter(ViewManagerModel viewManagerModel,
                           SignupViewModel signupViewModel,
                           LoginViewModel loginViewModel,
                           BrowseViewModel browseViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.browseViewModel = browseViewModel;
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
        signupState.setNameError(error);
        signupViewModel.firePropertyChanged();
    }
}
