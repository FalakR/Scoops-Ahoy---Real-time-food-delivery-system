package interface_adapters.login;

import interface_adapters.login.LoginController;
import use_cases.log_in.LoginInputBoundary;
import interface_adapters.login.LoginPresenter;
import interface_adapters.login.LoginViewModel;
import interface_adapters.ViewManagerModel;
import org.junit.Test;
import use_cases.log_in.LoginInputData;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class LoginControllerTest {

    @Test
    public void testLoginController() {
        // Arrange
        LoginInputBoundary mockLoginUseCaseInteractor = new MockLoginUseCaseInteractor();
        LoginController loginController = new LoginController(mockLoginUseCaseInteractor);

        // Act
        loginController.execute("john@example.com", "password123");

        // Assert
        MockLoginUseCaseInteractor mockInteractor = (MockLoginUseCaseInteractor) mockLoginUseCaseInteractor;
        assertTrue(mockInteractor.isExecuteCalled());
    }

    // Mock implementation for LoginInputBoundary
    private static class MockLoginUseCaseInteractor implements LoginInputBoundary {
        private boolean executeCalled;

        @Override
        public void execute(LoginInputData loginInputData) {
            executeCalled = true;
        }

        public boolean isExecuteCalled() {
            return executeCalled;
        }
    }
}