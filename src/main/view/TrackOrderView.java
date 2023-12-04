package view;

import entities.CommonLocation;
import entities.Location;
import interface_adapters.track_order.TrackOrderState;
import interface_adapters.track_order.TrackOrderViewModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.*;


public class TrackOrderView extends JFrame implements ActionListener, PropertyChangeListener {
    private final TrackOrderViewModel viewModel;
    public final String viewName = "track order";
    public JLabel title;
    private final JXMapViewer mapViewer;

    public TrackOrderView(TrackOrderViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        // Set title
        this.title = new JLabel("Scoops Ahoy - Track Order");
        this.title.setFont(new Font("Engravers Gothic BT", Font.BOLD, 24)); // Set font to Engravers Gothic BT
        this.title.setSize(200, 100);
        this.title.setBorder(BorderFactory.createEmptyBorder(32, 16, 24, 0));

        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new BorderLayout());


        // Map stuff

        JXMapViewer mapViewer = new JXMapViewer();

        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);

        tileFactory.setThreadPoolSize(4);

        this.mapViewer = mapViewer;

        JPanel mapView = new JPanel();
        mapView.setLayout(new BoxLayout(mapView, BoxLayout.Y_AXIS));
        mapView.add(this.title);
        mapView.add(mapViewer);
        mapView.setSize(new Dimension(1000, 625));
        this.mapViewer.setZoom(5);

        this.add(mapView);

//        setFields(
//                new TrackOrderState(
//                        new CommonLocation(43.675579, -79.402583),
//                        new CommonLocation(43.660636, -79.385996)
//                )
//        );

        pack();
        setLocationRelativeTo(null);
    }

    private void drawPoints(ArrayList<Location> locs) {
        WaypointPainter<Waypoint> wp = new WaypointPainter<>();
        Set<Waypoint> waypoints = new HashSet<>();

        for (Location loc: locs) {
            double x = loc.getX().doubleValue();
            double y = loc.getY().doubleValue();

            DefaultWaypoint waypoint = new DefaultWaypoint();
            waypoint.setPosition(new GeoPosition(x, y));

            waypoints.add(waypoint);
        }

        wp.setWaypoints(waypoints);
        this.mapViewer.setOverlayPainter(wp);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Empty because no event will be performed.
        // This view only displays the information it receives.
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        TrackOrderState state = (TrackOrderState) evt.getNewValue();
        if (state != null) {
            if (state.isSuccess) {
                this.title.setText("YOUR ORDER IS HERE!!!");
            } else {
                setFields(state);
            }
        }
    }

    private void setFields(TrackOrderState state) {
        ArrayList<Location> locs = new ArrayList<>();
        locs.add(
                new CommonLocation(
                        state.userLocation.getX().doubleValue(),
                        state.userLocation.getY().doubleValue()
                )
        );
        locs.add(
                new CommonLocation(
                        state.deliveryAgentLocation.getX().doubleValue(),
                        state.deliveryAgentLocation.getY().doubleValue()
                )
        );

        this.drawPoints(locs);

        this.mapViewer.setCenterPosition(
                new GeoPosition(
                        (state.deliveryAgentLocation.getX().doubleValue() + state.userLocation.getX().doubleValue()) / 2.0,
                        (state.deliveryAgentLocation.getY().doubleValue() + state.userLocation.getY().doubleValue()) / 2.0
                        )
        );
    }
}
