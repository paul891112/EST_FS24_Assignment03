# Cat Facts

## Questions
1. The external dependency is the cat facts API. We need to mock the API to test our CatFactsRetriever in isolation.
When testing CatFactsRetriever, we want to know if its methods are implemented correctly. Specifically, if a random fact is returned for retrieveRandom()
and if the longest fact is returned for retrieveLongest(int limit). We want to test the logic of these methods and not, if the communication of the API works correctly.
With mocking the API, we control what is returned by the get method of HttpUtil and can therefore check, if the logic of CatFactsRetriever is implemented correctly.
If we don't mock it, we rely on the correct implementation of HttpUtil which should be tested at another point and if a test fails, we don't know for sure if it fails because of the logic
of HttpUtil or CatFactsRetriever. 
2. To be able to mock HttpUtil and the API, we do not need to refactor by injecting the dependency because HttpUtil.get() is a static method.
Therefore, we can just use mockStatic to mock httpUtil and its get() method.
3. The major disadvantage I see with mocking HttpUtil and its get() method is, that the response could look different when we run the system as a whole.
We consider various cases of different responses (like empty JSON strings, empty strings etc.), but when running the system, the response from the API call
could look different. In a real life scenario we would have to specify exactly how the different responses from get() should look like, depending
on the response of the API call in HttpUtil. Also in general, we rely on a working implementation of HttpUtil. It should be tested thoroughly at another point
so we know, that it can handle various responses from the API call and therefore returns appropriate values when we call get() in CatFactsRetriever.

In conclusion, we test the different methods in isolation. When the system runs as a whole, we still depend on HttpUtil which could cause problems, even when our tests pass.
We need to test HttpUtil and its interaction with the API to make sure the system works when we run it as a whole. Additionally, System tests would be beneficial to test real life scenarios.
In our case, it makes sense to use mocks, because that way we can test the logic of our CatFactsRetriever class isolated from the dependency. We can be quite sure, that
its logic works properly and we fetch random facts as well as the longest facts, **if** the API implementation works and is correctly implemented
and returns appropriate values when get() is called.



## Testing Process

### Testing retrieveRandom()
To test, I partition the mock (the response of the get() method) as follows, so we can test retrieveRandom() works correctly in various cases:
- null 
- response is an empty string
- fact is an empty string
- fact is a string with length 1
- fact is a string with length > 1
- exception is thrown

Considering these partitions, I came up with the following test suite:
- T1: additional value: a string with a high length (also to get used to mocks)
- T2: null --> we want  a NullPointerException to be thrown in that case
- T3: whole response is an empty string --> added an if statement that throws exception in that case.
- T4: whole response is an empty JSON Object --> didn't consider that case --> we added an if statement that throws an appropriate exception if we get an empty json object as a response
- T5: fact is an empty string
- T6: fact is a string with length 1
- T7: fact is a string with length 2
- T8: exception is thrown

My test suite passes after applying the mentioned refactoring to CatFactsRetriever. I am happy with the test suite,
because we now know it can handle various responses of the dependency (the get call to HttpUtil). It works in cases where we get
a null response, an empty String as a response, an empty JSON String and various length's of fact in the JSON string.
However, the mentioned disadvantages from question 3 have to be considered.

### Testing retrieveLongest()

I partition again the response of get()
- null
- empty JSON Object () (when limit is 0)
- one fact (when limit is one)
- two facts (when limit is two)
- additional value: 5 facts (when limit is five)

 We need to assert, that it always returns the longest fact, no matter how many facts we fetch and no matter what is returned by get().

So, following test suite:
- T1: null is returned --> expected exception
- T2: no fact is returned (empty JsonObject) --> exception
- T3: one fact is returned --> same fact should be returned
- T4: two facts are returned --> longest one is returned (we test the case where the length of the fact differs of 1 here)
- T5: 5 facts are returned --> longest one is returned 
- T6: when limit is 0 --> exception should be thrown

With this test suite we test thoroughly what happens in different cases of limit (particularly, 0, 1 2 or higher)
and we test the different cases of the get() call to HttpUtil to reassure us that our function handles different return values appropriately.
Again, however, disadvantages in question 3 have to be considered.







