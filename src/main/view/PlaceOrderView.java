package view;

import interface_adapters.place_order.PlaceOrderController;
import interface_adapters.place_order.PlaceOrderState;
import interface_adapters.place_order.PlaceOrderViewModel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlaceOrderView  extends JFrame implements ActionListener, PropertyChangeListener {
    public final String viewName = "place order";

    private final PlaceOrderViewModel placeOrderViewModel;
    private final JTextField userAddressInputField = new JTextField(15);
    private final JPasswordField creditCardInputField = new JPasswordField(15);
    private final JPasswordField cvvInputField = new JPasswordField(15);
    private final JPasswordField expiryDateInputField = new JPasswordField(15);
    private final PlaceOrderController placeOrderController;

    private final JButton placeOrder;
    private final JButton cancel;

    public PlaceOrderView(PlaceOrderViewModel placeOrderViewModel, PlaceOrderController placeOrderController) {
        this.placeOrderViewModel = placeOrderViewModel;
        this.placeOrderController = placeOrderController;
        placeOrderViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Place Order");
        title.setFont(new Font("Engravers Gothic BT", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel userAddressInfo = new LabelTextPanel(
                new JLabel("User Address"), userAddressInputField);
        LabelTextPanel creditCardInfo = new LabelTextPanel(
                new JLabel("Credit Card Number"), creditCardInputField);
        LabelTextPanel cvvInfo = new LabelTextPanel(
                new JLabel("CVV"), cvvInputField);
        LabelTextPanel expiryDateInfo = new LabelTextPanel(
                new JLabel("Expiry Date"), expiryDateInputField);

        JPanel buttons = new JPanel();
        placeOrder = new JButton("Place Order");
        buttons.add(placeOrder);
        cancel = new JButton("Cancel");
        buttons.add(cancel);

        this.setBackground(new Color(253, 227, 245)); // Pastel Pink
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

                }
            }
        });

        cancel.addActionListener(this);

//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(userAddressInfo);
        this.add(creditCardInfo);
        this.add(cvvInfo);
        this.add(expiryDateInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle property changes in the PlaceOrderViewModel if needed
    }

}
