# Documentation for Real-Time Bus Tracking System

## Task A: Accuracy of Location Updates

I implemented a test to assert the accuracy of location updates in the `BusTracker` class. 
I mocked `GPSDeviceService` and `MapService` using Mockito,`GPSDeviceService` was set up to return a predefined `Location` object when its `getCurrentLocation` method was called. 
In the test I called `updateBusLocation` method of the `BusTracker` class and asserts that the `updateMap` method of the `MapService` was called with the correct parameters. 
This asserts that the `BusTracker` class accurately updates the bus's location on the map when new data is received from the `GPSDeviceService`.

## Task B: Notification of Key Events

I implemented a unit test to verify that the system correctly triggers notifications when buses arrive at key waypoints. 
Again I mocked `GPSDeviceService` and `NotificationService` using Mockito, the `GPSDeviceService` was set up to return a predefined `Location` object with the `keyWaypoint` property set to `true` when its `getCurrentLocation` method was called. 
In the `BusTracker` class the `updateBusLocation` method verifies that the `notifyPassengers` method of the `NotificationService` was called with the correct parameters. 
This asserts that the `BusTracker` class correctly triggers notifications when buses arrive at key waypoints.

## Task C: Response to GPS Signal Loss

I implemented a unit test to simulate the loss of GPS signal and verify how the system handles such failures. 
Again I mocked `GPSDeviceService` and `NotificationService` using Mockito, the `GPSDeviceService` was set up to return `null` when its `getCurrentLocation` method was called.
In the `BusTracker` class the `updateBusLocation` method verifies that the `notifyPassengers` method of the `NotificationService` was called with the correct parameters. 
This ensures that the `BusTracker` class correctly handles GPS signal loss and notifies passengers accordingly.

## Task D: Comparison

Using direct method calls in the `BusTracker` class we notify services about location changes. 
Doing it this way makes the code simpler and therefore easier to understand and debug.
The disadvantage is that it tightly couples the `BusTracker` class with the `GPSDeviceService`, `MapService`, and `NotificationService` classes, 
which can make the system less flexible and harder to change in the future.
Taking a more event-driven approach would decouple the `BusTracker` class from the other services, making the system more flexible and easier to change, but would also come with the disadvantage
of increased complexity and potentially more difficult debugging.

- Github Copilot was used to improve the format of the Documentation.md file. The prompt was "Reformat this file and highlight all file names and methods", including the Documentation.md file I wrote.