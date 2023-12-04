package app;

import entities.User;
import interface_adapters.ViewManagerModel;
import interface_adapters.add_to_cart.AddToCartViewModel;
import interface_adapters.login.LoginViewModel;
import org.junit.Before;
import org.junit.Test;
import use_cases.log_in.LoginDataAccessInterface;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

import static org.junit.Assert.*;

public class LoginUseCaseFactoryTest {

    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;
    private AddToCartViewModel browseViewModel;
    private TestLoginDataAccessObject userDataAccessObject;

    @Before
    public void setUp() {
        // Initialize necessary components or mocks
        viewManagerModel = new ViewManagerModel();
        loginViewModel = new LoginViewModel();
        browseViewModel = new AddToCartViewModel();
        userDataAccessObject = new TestLoginDataAccessObject();
    }

    @Test
    public void testCreate() {
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, browseViewModel, userDataAccessObject);

        assertNotNull(loginView);
    }

    // Mock implementation of LoginDataAccessObject for testing
    static class TestLoginDataAccessObject implements LoginDataAccessInterface {

        @Override
        public boolean existsByEmail(String email) {
            // Implement custom behavior for testing
            return false;
        }

        @Override
        public void save(User user) {
            // Implement custom behavior for testing
        }

        @Override
        public User get(String email) {
            // Implement custom behavior for testing
            return null;
        }

        // Additional methods if needed for testing

        // Override methods if needed for testing
    }
}
