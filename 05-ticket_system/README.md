# Customer Support Ticket System

## TicketManager Class
The `TicketManager` class manages customer support tickets for a software company. It integrates with third-party services for notifications (`NotificationService`) and logging (`LogService`). This class has a method named `createTicket` that performs the following actions:

- Creates a new support ticket.
- Logs the ticket creation using `LogService`.
- Notifies the customer using `NotificationService`.


## Unit Tests for the createTicket Method
Write *unit tests* for the `createTicket` method, considering the following points:

1. **Use of Doubles for NotificationService and LogService**
   - **Identify Dependencies**: Discuss which dependencies should be mocked and why.
   - **Test Doubles Usage**: Implement tests using mocks for `NotificationService` and `LogService` to verify if the services are called correctly.

2. **Disadvantages of Using Doubles in Your Tests**
   - Reflect on the drawbacks of using mocks in this scenario. 

3. **Handling of Failures in Notification and Logging**
   - Simulate failures in notification and logging. Ensure the ticket creation process is resilient and completed successfully despite these challenges.


**Automate the test cases using the JUnit5 plugin in the `src/test/java/zest/` folder.**

*Note 1*: Remember the techniques we learnt in the first chapters: test for empty cases, boundaries, etc. where applicable.

*Note 2*: Follow the **principles and best practices of good and maintainable test code**.

