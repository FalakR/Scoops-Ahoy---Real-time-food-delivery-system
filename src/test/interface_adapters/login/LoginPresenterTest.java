package interface_adapters.login;

import interface_adapters.ViewManagerModel;
import interface_adapters.add_to_cart.AddToCartViewModel;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginPresenterTest {

    @Test
    public void testPrepareSuccessView() {
        // Arrange
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        AddToCartViewModel addToCartViewModel = new AddToCartViewModel();

        LoginPresenter loginPresenter = new LoginPresenter(
                viewManagerModel, loginViewModel, addToCartViewModel);

        // Act
        loginPresenter.prepareSuccessView();

        // Assert
        assertNull(loginViewModel.getState().getEmailError());
        assertEquals(addToCartViewModel.getViewName(), viewManagerModel.getActiveView());
    }

    @Test
    public void testPrepareFailViewEmail() {
        // Arrange
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        AddToCartViewModel addToCartViewModel = new AddToCartViewModel();

        LoginPresenter loginPresenter = new LoginPresenter(
                viewManagerModel, loginViewModel, addToCartViewModel);

        String error = "User does not exist";

        // Act
        loginPresenter.prepareFailViewEmail(error);

        // Assert
        assertTrue(loginViewModel.isPropertyChanged());
        assertEquals(error, loginViewModel.getState().getEmailError());
    }

    @Test
    public void testPrepareFailViewPassword() {
        // Arrange
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        AddToCartViewModel addToCartViewModel = new AddToCartViewModel();

        LoginPresenter loginPresenter = new LoginPresenter(
                viewManagerModel, loginViewModel, addToCartViewModel);

        String error = "Incorrect password";

        // Act
        loginPresenter.prepareFailViewPassword(error);

        // Assert
        assertTrue(loginViewModel.isPropertyChanged());
        assertEquals(error, loginViewModel.getState().getPasswordError());
    }
}