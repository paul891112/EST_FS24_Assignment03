# Messages
The class `MessageProcessor` implements a simple messaging interface, using which a sender can send a text message to a receiver.
To do so, it uses a messaging service API (whose implementation is omitted) that depends on external factors (e.g., internet connection, correct installation).
`MessageProcessor` has a method `processMessages` that sends a number of messages to their receiver.

Write *unit tests* for the `processMessages` method using test doubles instead of the actual messaging service API, after refactoring the code accordingly if needed.
Specifically, implement tests the cover the following scenarios:

### A. Number of invocations
First, test if the appropriate subroutine is called as many times as it should.

### B. Content of invocations—`ArgumentCaptor`
Counting the number of invocations may not be enough; additionally, test if the content of the messages is as expected. Use `ArgumentCaptor` to do this.

### C. Content of invocations—Increasing observability
Instead of using `ArgumentCaptor`, you could increase the observability of the `MessageProcessor` class to achieve the same goal. 
Implement the necessary code for increasing the observability and write additional test(s) to verify whether the content of the messages is as expected.

### D. Comparison
What are the advantages and what are the disadvantages of the techniques you used in B. and C.?

Automate the test cases using the **JUnit5** plugin in the `src/test/java/zest/` folder.

*Note 1*: Remember the techniques we learnt in the first chapters: test for empty cases, boundaries, etc. where applicable.

*Note 2*: Follow the **principles and best practices of good and maintainable test code**.


