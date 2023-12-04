package view;

import interface_adapters.ViewManagerModel;
import interface_adapters.add_to_cart.AddToCartViewModel;
import interface_adapters.login.LoginController;
import interface_adapters.login.LoginPresenter;
import interface_adapters.login.LoginState;
import interface_adapters.login.LoginViewModel;
import interface_adapters.signup.SignupViewModel;
import org.junit.Before;
import org.junit.Test;
import use_cases.log_in.LoginInputBoundary;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import static org.junit.Assert.*;

public class LoginViewTest {

    private LoginViewModel loginViewModel;
    private TestLoginController loginController;
    private TestLoginPresenter loginPresenter;
    private LoginView loginView;
    private LoginInputBoundary loginInputBoundary;
    private ViewManagerModel viewManagerModel;

    @Before
    public void setUp() {
        loginViewModel = new LoginViewModel();
        viewManagerModel = new ViewManagerModel();
        loginController = new TestLoginController(loginInputBoundary);
        loginPresenter = new TestLoginPresenter(viewManagerModel, loginViewModel, new AddToCartViewModel());
        loginView = new LoginView(loginViewModel, loginController, loginPresenter);
    }

    @Test
    public void testActionPerformed() {
        // Example: Test the actionPerformed method
        loginView.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Login"));
        assertFalse(loginController.isExecuteCalled());
    }

    @Test
    public void testPropertyChangeWithEmptyEmailAndPassword() {
        // Example: Test the propertyChange method with empty email and password
        LoginState state = new LoginState();
        state.setEmailEmpty(true);
        state.setPasswordEmpty(true);
        PropertyChangeEvent event = new PropertyChangeEvent(this, "state", null, state);

        loginView.propertyChange(event);

        // Add assertions based on the expected behavior
        assertFalse(loginPresenter.isShowOptionDialogCalled());
        // Add more assertions as needed
    }

    @Test
    public void testPropertyChangeWithEmailError() {
        // Example: Test the propertyChange method with email error
        LoginState state = new LoginState();
        state.setEmailError("Invalid email");
        PropertyChangeEvent event = new PropertyChangeEvent(this, "state", null, state);

        loginView.propertyChange(event);

        // Add assertions based on the expected behavior
        assertFalse(loginPresenter.isShowOptionDialogCalled());
        // Add more assertions as needed
    }

    @Test
    public void testPropertyChangeWithPasswordError() {
        // Example: Test the propertyChange method with password error
        LoginState state = new LoginState();
        state.setPasswordError("Invalid password");
        PropertyChangeEvent event = new PropertyChangeEvent(this, "state", null, state);

        loginView.propertyChange(event);

        // Add assertions based on the expected behavior
        assertFalse(loginPresenter.isShowOptionDialogCalled());
        // Add more assertions as needed
    }

    // Mock implementation of TestLoginController for testing
    static class TestLoginController extends LoginController {

        private boolean executeCalled = false;

        public TestLoginController(LoginInputBoundary loginInputBoundary) {
            super(loginInputBoundary);
        }

        @Override
        public void execute(String email, String password) {
            executeCalled = true;
        }

        public boolean isExecuteCalled() {
            return executeCalled;
        }
    }

    // Mock implementation of TestLoginPresenter for testing
    static class TestLoginPresenter extends LoginPresenter {

        private boolean showOptionDialogCalled = false;

        public TestLoginPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, AddToCartViewModel addToCartViewModel) {
            super(viewManagerModel, loginViewModel, addToCartViewModel);
        }

        public boolean isShowOptionDialogCalled() {
            return showOptionDialogCalled;
        }
    }
}
