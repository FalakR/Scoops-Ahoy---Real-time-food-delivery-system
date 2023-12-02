package view;

import interface_adapters.track_order.TrackOrderState;
import interface_adapters.track_order.TrackOrderViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TrackOrderView extends JFrame implements ActionListener, PropertyChangeListener {
    private final TrackOrderViewModel viewModel;
    public final String viewName = "track order";

    public TrackOrderView(TrackOrderViewModel viewModel) {

        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        // Set title
        JLabel title = new JLabel("Scoops Ahoy");
        title.setFont(new Font("Engravers Gothic BT", Font.BOLD, 24)); // Set font to Engravers Gothic BT

        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new FlowLayout());
        JPanel mapDropIn = new JPanel(new FlowLayout());
        mapDropIn.setSize(600, 600);
        mapDropIn.setBackground(Color.GREEN);

        pack();

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Empty because no event will be performed.
        // This view only displays the information it receives.
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        TrackOrderState state = (TrackOrderState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(TrackOrderState state) {
        // TODO: set map stuff
        System.out.println(
                "Displaying map from " + state.deliveryAgentLocation + " to " + state.userLocation + "."
        );
    }
}
