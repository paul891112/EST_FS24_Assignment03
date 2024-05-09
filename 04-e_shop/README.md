# E-shop
An e-shop website invokes two actions after an order is placed. First, it updates the inventory database, since the
bought items may not be available for other orders anymore (this is implemented through the `InventoryManager` class);
second, it notifies the buyer by emailing them (this is implemented through the `EmailNotificationService` class).
Both of these classes inherit from the `EventListener` interface that has an `onOrderPlaced` method, which is invoked after an order is placed. For this invocation to happen, these classes have to subscribe to the `EventPublisher` class, which is in turn responsible for invoking the `onOrderPlaced` method of each listener.
Click [here](https://en.wikipedia.org/wiki/Observer_pattern) if you want to learn more about the *Observer* pattern (not mandatory for the assignment).

Write *unit tests* for the method `publishOrderPlaced` of the `EventPublisher` class using test doubles instead of the actual implementations of the email notification service and the inventory manager, after refactoring the code accordingly if needed.
Specifically, implement tests the cover the following scenarios:

### A. Number of invocations
First, test whether the `onOrderPlaced` method is called as many times as it should.

### B. Content of invocations—`ArgumentCaptor`
Counting the number of invocations may not be enough; additionally, test whether the contents of the order are as expected. Use `ArgumentCaptor` to do this.

### C. Content of invocations—Increasing observability
Instead of using `ArgumentCaptor`, you could increase the observability of one or more classes to achieve the same goal.
Implement the necessary code for increasing the observability and write additional test(s) to test whether the content of the messages is as expected.

### D. Comparison
What are the advantages and what are the disadvantages of the techniques you used in B. and C.?

Automate the test cases using the **JUnit5** plugin in the `src/test/java/zest/` folder.

*Note 1*: Remember the techniques we learnt in the first chapters: test for empty cases, boundaries, etc. where applicable.

*Note 2*: Follow the **principles and best practices of good and maintainable test code**.

