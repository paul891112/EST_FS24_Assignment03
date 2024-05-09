# Payment Processing System

The `PaymentProcessor` class handles financial transactions for an online marketplace. This class utilizes a TransactionService for processing payments and a FraudDetectionService to ensure transaction security. After a transaction is completed, the PaymentProcessor uses an AuditService to log transaction details. The AuditService subscribes to transaction completion events through the EventPublisher class, which invokes the onTransactionComplete method of each listener after a transaction is processed.

Write *unit tests* for the `publishTransactionComplete` method of the `EventPublisher`  class using test doubles, after refactoring the code accordingly if needed.

Specifically, implement tests that cover the following scenarios:

### A. Number of invocations
First, test whether the `onTransactionComplete` method is called as many times as it should.

### B. Content of invocations—`ArgumentCaptor`
Counting the number of invocations may not be enough; additionally, test whether the contents of the transaction details are as expected as passed to the AuditService. Use `ArgumentCaptor` to do this.


### C. Content of invocations—Increasing observability
Instead of using `ArgumentCaptor`, you could increase the observability of the `PaymentProcessor` class to achieve the same goal.
Implement the necessary code for increasing the observability and write additional test(s) to test whether the content of the messages is as expected.

### D. Comparison
What are the advantages and what are the disadvantages of the techniques you used in B. and C.?

Automate the test cases using the **JUnit5** plugin in the `src/test/java/zest/` folder.

*Note 1*: Remember the techniques we learnt in the first chapters: test for empty cases, boundaries, etc. where applicable.

*Note 2*: It is important to follow the **principles and best practices of good and maintainable test code**.
