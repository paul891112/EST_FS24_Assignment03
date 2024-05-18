# 04 E-Shop

## Set up mocks

We mock three classes: one for InventoryManager, one for EmailNotificationService, one for EventListener.

For asserting correct setup, I define a get function to check the state of the variable listeners.

## A. Number of invocation

There are four cases associated with testing the number of invocation. First, the correct behavior for one 
InventoryManager, one EmailNotificationService, and one of each is tested. The fourth test case asserts that 
multiple eventListeners subscribe to the publisher executes method under test the correct amount of time.

## B. Content of invocation - ArgumentCaptor

For each call of publishOrderToAllListeners(), the argument order is passed to it's subroutine. This parameter  
is captured by with ArgumentCaptor and recorded. After execution, every parameter passed to the subroutine is 
compared with the original order. Every element captured by ArgumentCaptor correspond exactly to the original 
order. 

## C. Content of invocation - increasing observability

Since EventListener Interface is the abstraction to some (external) service, they shouldn't be changed for the 
sake of observability. To keep track of every parameter passed to subroutine of publishOrderToAllListeners(), it 
saves all values passed to a subroutine as string in a list, and returns it after execution so that the arguments 
can be examined. For this example, we compare every element of the list of parameters with the original order 
object converted to string. 

If we propagate this change lower down in the design, such that InventoryManager and EmailNotificationService also 
return a string, the return value won't be captured without further changes in the test code. Since we are mocking 
the interface or the actual service objects, we will have to stub the behavior of the methods defined in subroutine, 
which beats the purpose of increasing testability with increased observability. 

## D. Comparison

In this example, the ArgumentCaptor is more suited for testing purpose because the method under test is a subroutine 
which is abstracted away from the tester, there is no true value if we increase observability in the subroutine. As 
mentioned, we could inject the dependency down in the design, but will also have to stub the behavior of subroutines.
On the other hand, by using ArgumentCaptor we can examine the input parameter even though the method is on a lower level 
in design and without changing much of existing code. 
