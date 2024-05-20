# Customer Support Ticket System

# Unit Tests for the createTicket Method

1. **Use of Doubles for NotificationService and LogService**
    - **Identify Dependencies**: In order to write the unit tests for `createTicket` I had to mock `NotificationService` and `LogService`. 
         These are external services which for testing purposes don't want to be called. In the same category is `TicketRepository` which is database which we also mock to avoid flaky tests.
         Therefore I identified all dependencies and mocked them to isolate the unit of code we are testing.
    - **Test Doubles Usage**: I implemented tests using mocks for `NotificationService` and `LogService` to verify if the services are called correctly. 
          To do this Mockito's `verify` method ensured that the methods in these services are called with the correct parameters.

2. **Disadvantages of Using Doubles in Your Tests**
    - Mocks provide the ability to isolate a unit of code, which we are testing, from its dependencies. There are disadvantages that come with this:
      - First, there are false positives, since we are not testing with real objects, the tests might pass even when there is a problem with the actual integration with the real services.
      - Further, there could also be false positives if the implementation of the external services changes, the tests might still pass because the mocks are not updated.

3. **Handling of Failures in Notification and Logging**
    - By simulating failures in notification and logging, by making the mock objects throw exceptions, I ensure that the ticket creation process is resilient and completed successfully despite these challenges. 
      To ensure that no exceptions are thrown when these failures occur, I used the `assertDoesNotThrow` method from JUnit.

Github Copilot was used to improve the format of the Documentation.md file. The prompt was "Reformat this file and highlight all file names and methods", including the Documentation.md file I wrote.