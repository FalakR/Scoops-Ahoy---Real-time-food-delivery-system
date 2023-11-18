package view;


import interface_adapters.notification.NotifController;
import interface_adapters.notification.NotifState;
import interface_adapters.notification.NotifViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class NotifView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "notification";

    private final NotifViewModel notifViewModel;
    private final NotifController notifController;

    JLabel courier;
    final JButton startNewOrder;
    public NotifView(NotifViewModel notifViewModel, NotifController notifController) {
        this.notifViewModel = notifViewModel;
        this.notifController = notifController;



        JLabel title = new JLabel("Notification Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel courierInfo = new JLabel("Your courier is here with your order: ");
        courier = new JLabel();

        JPanel buttons = new JPanel();
        startNewOrder = new JButton(NotifViewModel.NOTIF_BUTTON_LABEL);
        buttons.add(startNewOrder);

        startNewOrder.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(courierInfo);
        this.add(courier);
        this.add(buttons);

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        NotifState state = (NotifState) evt.getNewValue();
        courier.setText(state.getCourierName());
    }
}

