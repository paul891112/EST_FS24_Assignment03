# 03-Messages


## Change production code to facilitate testing

First step I took is to refactor the code in production such that testing with doubles is possible.

By specifying the messageService as input parameter to processMessage(), as a tester I now have control 
over the dependency and can use mock objects to test the behavior of processMessage() that I couldn't 
do before.

## Set up mocks

In this exercise, the messageService poses an external dependency. The implementation is hidden away from the tester, 
therefore a mock object for this instance is necessary for testing purpose. We also stub the behavior of sendMessage() 
such that when ever processMessages() calls this subroutine, it doesn't execute the API call. Alternatively, we can 
define certain behavior to replace the original such that testing is made easier, but this will add uncertainty to the 
method under test.

## A. Number of invocation

The method under test processMessages() calls the subroutine messageService.sendMessage() as many times 
as the size of input parameter messages, which is a list of Messages. For unit testing purposes, test 
cases with input size 0, 1, 5 and random size are added to test suite. An automated test cases is added in addition.
All tests pass.

## B. Content of invocation - ArgumentCaptor 

Devise a test case that generates 5 messages, keeps the receivers and contents in seperate ArrayLists, and compares 
them with values extracted with ArgumentCaptor. Both collections contain exactly the same elements. The test passes.

The empty input list doesn't deserve to be tested, since we have already asserted that messageService is 
not executed when passing an empty list as parameter. Tests pass as expected.

## C. Content of invocation - increased Observability

Instead of void, I changed the return type of method under test to List<String> such that the argument captor is not 
needed anymore since the passed parameter are returned as list of strings with ":" delimiting the values of receiver 
and content.

To test for correctness, when generating the input array list, the receiver and content of the message are merged to 
one string and kept in a seperate array list. This array is compared to the return list of processMessages().

While changing the code, I try to only modify processMessages() because it is the only method under test.

## D. Comparisons

In this example, since two arguments are passed to the subroutine messageService.sendMessage(), also two
ArgumentCaptor instances will be needed for testing purpose. If a subroutine requires many arguments and thus many
ArgumentCaptors, the possibility to return the parameters in a list can be beneficial to the ease of testing. On the 
other hand, if a large input list is passed to method under test, the return list can be very large too, which is less 
favorable compared to using ArgumentCaptor. Moreover, observability doesn't play a major role in this method due to 
it's simplicity and straight-forward implementation.

In general, since having a short parameter list to a method is considered good practice for software development, the 
ArgumentCaptor will be more useful for testing void methods that don't require high observability. However, it poses a 
tradeoff that may favor one implementation or the other depending on the situation. 