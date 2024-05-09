# Real-Time Bus Tracking System

The `BusTracker` class manages the real-time locations of buses as they move throughout a city. The system integrates with a GPSDeviceService to receive live location data from buses and a MapService to update the positions on a public-facing map. Additionally, when a bus reaches certain key waypoints or stations, it triggers notifications to passengers via the NotificationService.

Write *unit tests* for the `updateBusLocation` method of the `BusTracker` class using test doubles for the GPS and map services.

Specifically, implement tests that cover the following scenarios:

### A. Accuracy of Location Updates
First, verify that the `updateBusLocation` method accurately updates the bus's location on the map when new data is received.

### B. Notification of Key Events
Test if the system correctly triggers notifications when buses arrive at key waypoints. Use `ArgumentCaptor` to capture the details passed to the NotificationService.


### C.  Response to GPS Signal Loss
Implement tests that simulate the loss of GPS signal to see how the system handles failures or interruptions in data.

### D. Comparison
Evaluate the effectiveness of direct method calls versus event-driven updates for notifying services about location changes.

Automate the test cases using the **JUnit5** plugin in the `src/test/java/zest/` folder.

*Note 1*: Remember the techniques we learnt in the first chapters: test for empty cases, boundaries, etc. where applicable.

*Note 2*: Follow the **principles and best practices of good and maintainable test code**.
