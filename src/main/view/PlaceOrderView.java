package view;

import interface_adapters.place_order.PlaceOrderController;
import interface_adapters.place_order.PlaceOrderPresenter;
import interface_adapters.place_order.PlaceOrderState;
import interface_adapters.place_order.PlaceOrderViewModel;
import interface_adapters.signup.SignupState;
import use_cases.place_order.PlaceOrderOutputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlaceOrderView extends JFrame implements ActionListener, PropertyChangeListener {
    public final String viewName = "place order";

    private final PlaceOrderViewModel placeOrderViewModel;
    private final JTextField userAddressInputField = new JTextField(15);
    private final JPasswordField creditCardInputField = new JPasswordField(15);
    private final JPasswordField cvvInputField = new JPasswordField(15);
    private final JPasswordField expiryDateInputField = new JPasswordField(15);
    private final PlaceOrderController placeOrderController;

    private final JButton placeOrder;
    private final JButton cancel;

    public PlaceOrderView(PlaceOrderViewModel placeOrderViewModel, PlaceOrderController placeOrderController, PlaceOrderPresenter placeOrderPresenter) {
        this.placeOrderViewModel = placeOrderViewModel;
        this.placeOrderController = placeOrderController;
        placeOrderViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Place Order");
        title.setFont(new Font("Engravers Gothic BT", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        LabelTextPanel userAddressInfo = new LabelTextPanel(
                new JLabel("User Address"), userAddressInputField);
        LabelTextPanel creditCardInfo = new LabelTextPanel(
                new JLabel("Credit Card Number"), creditCardInputField);
        LabelTextPanel cvvInfo = new LabelTextPanel(
                new JLabel("CVV"), cvvInputField);
        LabelTextPanel expiryDateInfo = new LabelTextPanel(
                new JLabel("Expiry Date"), expiryDateInputField);

        contentPanel.add(title);
        contentPanel.add(userAddressInfo);
        contentPanel.add(creditCardInfo);
        contentPanel.add(cvvInfo);
        contentPanel.add(expiryDateInfo);

        JPanel buttons = new JPanel();
        placeOrder = new JButton("Place Order");
        buttons.add(placeOrder);
        cancel = new JButton("Cancel");
        buttons.add(cancel);

        placeOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(placeOrder)) {
                    PlaceOrderState currentState = placeOrderViewModel.getState();

                    PlaceOrderView.this.placeOrderController.execute(
                            currentState.getCart(),
                            currentState.getIceCreams(),
                            currentState.getAddress(),
                            currentState.getCardNumber(),
                            currentState.getCvv(),
                            currentState.getExpiryDate()
                    );
                    placeOrderPresenter.prepareChangeView();
                }
            }
        });
        userAddressInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        PlaceOrderState currentState = placeOrderViewModel.getState();
                        currentState.setAddress(userAddressInputField.getText() + e.getKeyChar());
                        placeOrderViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        creditCardInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        PlaceOrderState currentState = placeOrderViewModel.getState();
                        currentState.setAddress(String.valueOf(creditCardInputField.getText()) + e.getKeyChar());
                        placeOrderViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        cvvInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        PlaceOrderState currentState = placeOrderViewModel.getState();
                        currentState.setAddress(String.valueOf(cvvInputField.getText()) + e.getKeyChar());
                        placeOrderViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        expiryDateInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        PlaceOrderState currentState = placeOrderViewModel.getState();
                        currentState.setAddress(String.valueOf(expiryDateInputField.getText()) + e.getKeyChar());
                        placeOrderViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        contentPanel.add(buttons);

        this.add(contentPanel);

        this.setBackground(new Color(253, 227, 245)); // Pastel Pink
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
