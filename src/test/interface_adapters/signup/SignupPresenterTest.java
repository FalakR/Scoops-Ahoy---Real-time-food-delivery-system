package interface_adapters.signup;

import interface_adapters.ViewManagerModel;
import interface_adapters.add_to_cart.AddToCartViewModel;
import interface_adapters.login.LoginViewModel;
import junit.framework.TestCase;
import org.junit.Test;
import use_cases.sign_up.SignupOutputBoundary;
import

import static org.junit.Assert.assertTrue;

//public class SignupPresenterTest {
//
//    @Test
//    public void testSignupPresenter() {
//        // Arrange
//        ViewManagerModel viewManagerModel = new MockViewManagerModel();
//        SignupViewModel signupViewModel = new MockSignupViewModel();
//        LoginViewModel loginViewModel = new MockLoginViewModel();
//        AddToCartViewModel addToCartViewModel = new MockAddToCartViewModel();
//
//        SignupOutputBoundary signupPresenter = new SignupPresenter(
//                viewManagerModel, signupViewModel, loginViewModel, addToCartViewModel);
//
//        // Act
//        signupPresenter.prepareSuccessView();
//        signupPresenter.prepareFailView("User already exists.");
//
//        // Assert
//        assertTrue(((SignupPresenter) signupPresenter).isPrepareSuccessViewCalled());
//        assertTrue(((SignupPresenter) signupPresenter).isPrepareFailViewCalled());
//    }
//
//    // Mock implementations for required dependencies
//    private static class MockViewManagerModel implements ViewManagerModel {
//        // Implement necessary methods for testing
//    }
//
//    private static class MockSignupViewModel implements SignupViewModel {
//        // Implement necessary methods for testing
//    }
//
//    private static class MockLoginViewModel implements LoginViewModel {
//        // Implement necessary methods for testing
//    }
//
//    private static class MockAddToCartViewModel implements AddToCartViewModel {
//        // Implement necessary methods for testing
//    }
//
//    // Mock implementation for SignupOutputBoundary
//    private static class SignupPresenter implements SignupOutputBoundary {
//        private boolean prepareSuccessViewCalled;
//        private boolean prepareFailViewCalled;
//
//        @Override
//        public void prepareSuccessView() {
//            prepareSuccessViewCalled = true;
//        }
//
//        @Override
//        public void prepareLoginView() {
//            // Not needed for this test
//        }
//
//        @Override
//        public void prepareFailView(String error) {
//            prepareFailViewCalled = true;
//        }
//
//        public boolean isPrepareSuccessViewCalled() {
//            return prepareSuccessViewCalled;
//        }
//
//        public boolean isPrepareFailViewCalled() {
//            return prepareFailViewCalled;
//        }
//    }
//}
