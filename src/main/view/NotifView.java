package view;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class NotifView extends JPanel implements PropertyChangeListener {
    public final String viewName = "Notification View";
    private final JLabel deliveryStatusLabel;

    public NotifView() {

        deliveryStatusLabel = new JLabel();
        deliveryStatusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        deliveryStatusLabel.setBackground(new Color(255, 250, 205));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(deliveryStatusLabel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String message = "Your order has been delivered.";
        displayDeliveryStatus(message);
    }

    private void displayDeliveryStatus(String message) {
        deliveryStatusLabel.setText(message);
    }
}
