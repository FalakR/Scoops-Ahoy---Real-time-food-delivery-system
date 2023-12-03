package interface_adapters.signup;

import interface_adapters.ViewManagerModel;
import interface_adapters.add_to_cart.AddToCartViewModel;
import interface_adapters.login.LoginViewModel;
import junit.framework.TestCase;
import org.junit.Test;
import use_cases.sign_up.SignupOutputBoundary;
//import
import static org.junit.Assert.*;


public class SignupPresenterTest {

    @Test
    public void testPrepareSuccessView() {
        // Arrange
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        AddToCartViewModel addToCartViewModel = new AddToCartViewModel();

        SignupPresenter signupPresenter = new SignupPresenter(
                viewManagerModel, signupViewModel, loginViewModel, addToCartViewModel);

        // Act
        signupPresenter.prepareSuccessView();

        // Assert
        // Assuming browseViewModel is initially set to a default value
        assertNull(signupViewModel.getState().getEmailError());  // Check a relevant state in SignupViewModel
        assertEquals(addToCartViewModel.getViewName(), viewManagerModel.getActiveView());
    }


    @Test
    public void testPrepareLoginView() {
        // Arrange
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        AddToCartViewModel addToCartViewModel = new AddToCartViewModel();

        SignupPresenter signupPresenter = new SignupPresenter(
                viewManagerModel, signupViewModel, loginViewModel, addToCartViewModel);

        // Act
        signupPresenter.prepareLoginView();

        // Assert
        assertTrue(loginViewModel.isPropertyChanged());
        assertEquals(loginViewModel.getViewName(), viewManagerModel.getActiveView());
    }

    @Test
    public void testPrepareFailView() {
        // Arrange
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        AddToCartViewModel addToCartViewModel = new AddToCartViewModel();

        SignupPresenter signupPresenter = new SignupPresenter(
                viewManagerModel, signupViewModel, loginViewModel, addToCartViewModel);

        String error = "User already exists";

        // Act
        signupPresenter.prepareFailView(error);

        // Assert
        assertTrue(signupViewModel.isPropertyChanged());
        assertEquals(error, signupViewModel.getState().getEmailError());
    }
}
