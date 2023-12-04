package app;

import data_access.FileUserDataAccessObject;
import entities.CommonUserFactory;
import interface_adapters.ViewManagerModel;
import interface_adapters.add_to_cart.AddToCartViewModel;
import interface_adapters.login.LoginViewModel;
import interface_adapters.signup.SignupViewModel;
import org.junit.Before;
import org.junit.Test;
import view.SignupView;

import static org.junit.Assert.*;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactoryTest {

    private static class TestFileUserDataAccessObject extends FileUserDataAccessObject {
        public TestFileUserDataAccessObject() throws IOException {
            super("test_user_data.csv", new CommonUserFactory()); // Provide a test CSV path
        }
    }

    private ViewManagerModel viewManagerModel;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private AddToCartViewModel browseViewModel;
    private TestFileUserDataAccessObject userDataAccessObject;

    @Before
    public void setUp() {
        // Initialize necessary dependencies
        viewManagerModel = new ViewManagerModel();
        signupViewModel = new SignupViewModel();
        loginViewModel = new LoginViewModel();
        browseViewModel = new AddToCartViewModel();

        try {
            userDataAccessObject = new TestFileUserDataAccessObject();
        } catch (IOException e) {
            // Handle IOException during setup if needed
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateSignupViewSuccess() {
        SignupView signupView = SignupUseCaseFactory.create(
                viewManagerModel, loginViewModel, signupViewModel, browseViewModel, userDataAccessObject);

        // Verify that the SignupView is created successfully
        assertNotNull(signupView);

        // Add more assertions as needed based on the expected behavior
    }
}