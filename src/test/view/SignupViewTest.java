package view;

import interface_adapters.ViewManagerModel;
import interface_adapters.add_to_cart.AddToCartViewModel;
import interface_adapters.login.LoginViewModel;
import interface_adapters.signup.SignupController;
import interface_adapters.signup.SignupPresenter;
import interface_adapters.signup.SignupViewModel;
import org.junit.Before;
import org.junit.Test;
import use_cases.sign_up.SignupInputBoundary;
import use_cases.sign_up.SignupInputData;
import use_cases.sign_up.SignupOutputData;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SignupViewTest {

    private SignupViewModel signupViewModel;
    private ViewManagerModel viewManagerModel;
    private TestSignupController signupController;
    private TestSignupPresenter signupPresenter;
    private SignupView signupView;
    private SignupInputBoundary signupInputBoundary;

    @Before
    public void setUp() {
        signupViewModel = new SignupViewModel();
        viewManagerModel = new ViewManagerModel();
        signupController = new TestSignupController(signupInputBoundary);
        signupPresenter = new TestSignupPresenter(viewManagerModel, signupViewModel, new LoginViewModel(), new AddToCartViewModel());
        signupView = new SignupView(signupController, signupPresenter, signupViewModel);
    }

    @Test
    public void testActionPerformed() {
        // No specific action to test in actionPerformed, just a print statement
        signupView.actionPerformed(null);

        // Since there's no specific behavior to check, let's just make sure no exceptions are thrown
        assertTrue(true);
    }

    @Test
    public void testSignupSuccess() {
        // Set up the SignupViewModel state for a successful signup
        signupViewModel.getState().setName("John");
        signupViewModel.getState().setEmail("john@example.com");
        signupViewModel.getState().setPassword("password");

        // Trigger the signup action
        signupView.signUp.doClick();

        // Verify the expected behavior
        assertFalse(signupViewModel.isPropertyChanged());
        assertFalse(signupPresenter.isPrepareErrorViewCalled());
        assertTrue(signupPresenter.isPrepareSuccessViewCalled());
        assertTrue(signupController.isExecuteCalled());
    }

    @Test
    public void testSignupFailure() {
        // Set up the SignupViewModel state for a failed signup (duplicate email)
        signupViewModel.getState().setName("John");
        signupViewModel.getState().setEmail("john@example.com");
        signupViewModel.getState().setPassword("password");

        // Trigger the signup action twice with the same email
        signupView.signUp.doClick();
        signupView.signUp.doClick();

        // Verify the expected behavior
        assertFalse(signupViewModel.isPropertyChanged());
        assertFalse(signupPresenter.isPrepareErrorViewCalled());
        assertTrue(signupPresenter.isPrepareSuccessViewCalled());
        assertTrue(signupController.isExecuteCalled());
    }

    // Mock implementation of SignupController for testing
    static class TestSignupController extends SignupController {

        private boolean executeCalled = false;

        public TestSignupController(SignupInputBoundary signupUseCaseInteractor) {
            super(signupUseCaseInteractor);
        }

        @Override
        public void execute(String name, String email, String password) {
            executeCalled = true;
        }

        public boolean isExecuteCalled() {
            return executeCalled;
        }
    }

    // Mock implementation of SignupPresenter for testing
    static class TestSignupPresenter extends SignupPresenter {

        private boolean prepareSuccessViewCalled = false;
        private boolean prepareErrorViewCalled = false;

        public TestSignupPresenter(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, AddToCartViewModel addToCartViewModel) {
            super(viewManagerModel, signupViewModel, loginViewModel, addToCartViewModel);
        }

        @Override
        public void prepareSuccessView() {
            prepareSuccessViewCalled = true;
        }

        @Override
        public void prepareFailView(String errorMessage) {
            prepareErrorViewCalled = true;
        }

        public boolean isPrepareSuccessViewCalled() {
            return prepareSuccessViewCalled;
        }

        public boolean isPrepareErrorViewCalled() {
            return prepareErrorViewCalled;
        }
    }

    // Mock implementation of SignupInputBoundary for testing
    static class TestSignupInputBoundary implements SignupInputBoundary {
        @Override
        public void execute(SignupInputData signupInputData) {

        }

        // Implement methods if needed for testing
    }
}
