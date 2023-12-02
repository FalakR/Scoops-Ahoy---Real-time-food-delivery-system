package app;

import data_access.AblyDataAccessObject;
import data_access.FileUserDataAccessObject;
import data_access.InMemoryDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entities.CommonUserFactory;
import interface_adapters.ViewManagerModel;

import interface_adapters.add_to_cart.AddToCartViewModel;
import interface_adapters.login.LoginViewModel;
import interface_adapters.signup.SignupViewModel;

import interface_adapters.track_order.TrackOrderViewModel;
import io.ably.lib.types.AblyException;
import view.LoginView;
import view.SignupView;
import view.TrackOrderView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        AddToCartViewModel browseViewModel = new AddToCartViewModel();
        TrackOrderViewModel trackOrderViewModel = new TrackOrderViewModel();


        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        AblyDataAccessObject ablyDataAccessObject;
        try {
            ablyDataAccessObject = new AblyDataAccessObject();
        } catch (AblyException e) {
            throw new RuntimeException(e);
        }

        InMemoryDataAccessObject inMemoryDataAccessObject = new
                InMemoryDataAccessObject();

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, browseViewModel, userDataAccessObject);
        views.add(signupView.getContentPane(), signupView.viewName);
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, browseViewModel, userDataAccessObject);
        views.add(loginView.getContentPane(), loginView.viewName);

        TrackOrderView trackOrderView = TrackOrderUseCaseFactory.create(
                viewManagerModel,
                trackOrderViewModel,
                ablyDataAccessObject,
                inMemoryDataAccessObject
        );
        views.add(trackOrderView, trackOrderView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setLocationRelativeTo(null);
        application.setSize(1000,700);
        application.setVisible(true);
    }
}