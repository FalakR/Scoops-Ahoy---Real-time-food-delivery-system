package interface_adapters.track_order;

import entities.CommonLocation;
import entities.Location;
import interface_adapters.ViewManagerModel;
import org.junit.Test;
import use_cases.track_order.TrackOrderOutputData;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.Assert.*;

public class TrackOrderPresenterTest {
    @Test
    public void test() {
        TrackOrderViewModel viewModel = new TrackOrderViewModel();
        final TrackOrderState[] state = new TrackOrderState[1];
        Location userLocation = new CommonLocation(1.0, 1.0);
        Location deliveryAgentLocation = new CommonLocation(2.0, 2.0);

        viewModel.addPropertyChangeListener(
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        state[0] = (TrackOrderState) evt.getNewValue();
                    }
                }
        );

        TrackOrderPresenter presenter = new TrackOrderPresenter(
                new ViewManagerModel(), viewModel
        );

        // Act 1: location update

        presenter.prepareView(
                new TrackOrderOutputData(
                        userLocation,
                        deliveryAgentLocation
                )
        );

        assertEquals(
                deliveryAgentLocation,
                state[0].deliveryAgentLocation
        );

        assertEquals(
                userLocation,
                state[0].userLocation
        );


        // Act 2: success view

        presenter.prepareSuccessView();

        assertTrue(state[0].isSuccess);
    }
}