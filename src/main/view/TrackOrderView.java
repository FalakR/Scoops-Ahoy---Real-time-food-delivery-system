package view;

import entities.CommonLocation;
import entities.Location;
import interface_adapters.track_order.TrackOrderState;
import interface_adapters.track_order.TrackOrderViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

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
        this.add(title);


        // Map stuff
        Location loc = new CommonLocation(43.6690207,-79.3916043);

        JXMapViewer mapViewer = new JXMapViewer();

        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);

        tileFactory.setThreadPoolSize(4);


        GeoPosition pos = new GeoPosition(loc.getX().doubleValue(), loc.getY().doubleValue());

        mapViewer.setZoom(8);
        mapViewer.setAddressLocation(pos);

        mapViewer.setPreferredSize(new Dimension(1000, 625));
        this.add(mapViewer);

        pack();
        setLocationRelativeTo(null);
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
