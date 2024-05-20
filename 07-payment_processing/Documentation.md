# Documentation

## Task A: Number of Invocations

I wrote a test that asserts whether the `onTransactionComplete` method is called as many times as it should. 
Mockito's `verify` method checks that `onTransactionComplete` was called exactly once.  
Since the test passes, I know that the `onTransactionComplete` method was correctly called one time as it should in this case.

## Task B: Content of Invocations—`ArgumentCaptor`

`ArgumentCaptor` captures the argument passed to `onTransactionComplete` and checks if it is the expected `Transaction` object. 
This allows to inspect the arguments of method calls with mock objects. 
Since the test passes, I know the correct `Transaction` object was passed to `onTransactionComplete`.

## Task C: Content of Invocations—Increasing Observability

To increase the observability of the `PaymentProcessor` class I added a `getLastProcessedTransaction` method. 
In the new `PaymentProcessorTest` class I wrote a test `testPaymentProcessorObservability` that checks if `getLastProcessedTransaction` returns the correct `Transaction` object.
Since the test passes, I know that the `PaymentProcessor` correctly updates its last processed transaction when `processPayment` is called.

## Task D: Comparison

There are advantages and disadvantages for both `ArgumentCaptor` and increasing observability.
**ArgumentCaptor**: Useful to capture and inspect the argument used for method calls on mock objects.
- Allows for testing how the code interacts with its dependencies. 
- Makes tests become tightly coupled to the implementation details of the code. This can lead to tests that break more easily when the implementation changes. 

**Increasing observability**: Useful for easier monitoring and debugging of the code.
- Decouples the tests from the implementation details of the code. This can lead to more robust tests that are less likely to break when the implementation changes.
- Requires modification of the code itself, which is not always wanted or possible or even allowed.

Github Copilot was used to improve the format of the Documentation.md file. The prompt was "Reformat this file and highlight all file names and methods", including the Documentation.md file I wrote.
