package zest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BusTrackerTest {
    @Mock
    private GPSDeviceService gpsService;
    @Mock
    private MapService mapService;
    @Mock
    private NotificationService notificationService;
    @Captor
    private ArgumentCaptor<String> stringCaptor;
    @Captor
    private ArgumentCaptor<Location> locationCaptor;

    private BusTracker busTracker;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        busTracker = new BusTracker(gpsService, mapService, notificationService);
    }

    @Test
    public void testAccuracyOfLocationUpdates() {
        Location location = new Location(10.0, 20.0, false, "Test Waypoint");
        when(gpsService.getCurrentLocation(anyString())).thenReturn(location);

        busTracker.updateBusLocation("Bus1");

        verify(mapService).updateMap(eq("Bus1"), locationCaptor.capture());
        assertEquals(location, locationCaptor.getValue());
    }

    @Test
    public void testNotificationOfKeyEvents() {
        Location location = new Location(10.0, 20.0, true, "Test Waypoint");
        when(gpsService.getCurrentLocation(anyString())).thenReturn(location);

        busTracker.updateBusLocation("Bus1");

        verify(notificationService).notifyPassengers(eq("Bus1"), stringCaptor.capture());
        assertEquals("The bus has arrived at Test Waypoint", stringCaptor.getValue());
    }

    @Test
    public void testResponseToGPSSignalLoss() {
        when(gpsService.getCurrentLocation(anyString())).thenReturn(null);

        busTracker.updateBusLocation("Bus1");

        verify(notificationService).notifyPassengers(eq("Bus1"), stringCaptor.capture());
        assertEquals("GPS signal lost. Please check back later.", stringCaptor.getValue());
    }
}