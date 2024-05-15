# Book Reviews

## A. Get high-rated books

### Testing Process
First, I needed to make the mock work for which I did a simple base case with three books where we have two high ratings.

From the documentation it isn't clear if a high rating includes 4. So, I assumed a high rating is >= 4.

I treated the mock (list of books) the same as an input in a simple specification-based testing process.

Therefore, I derived the following partitions for our mock:

I want to test, different lengths of lists (null, empty, one and two) and different ratings as follows:
- on-point = 4
- off-point = 3
- in-point = 5
- out-point = 0

Originating from these partitions, I came up with the following test cases, considering the length of the list and the boundaries:
- T1 null list --> found a bug, where bookManager doesn't consider a null list returned by the fetcher. I added an if statement that throws an exception in that case.
- T2 empty list 
- T3 list length one low rating (off-point)
- T4 list length one high rating (on-point)
- T5 list length two only low ratings (out-point)
- T6 list length two only high ratings (in-point)
- T7 list length two one high and one low rating (on and off point)

With above test suite, I thoroughly test the boundaries as well as the correctness of the algorithm with different list lengths.

### Questions
1. The external dependencies are the database and the fetcher (The fetcher depends on the database). We need to stub, what the fetcher returns to simulate the behavior of the fetcher with a real database.
That way, we can test if the logic of the highRatedBooks() method is correct (specifically, if it filters the correct books) and at the same time don't depend on the functionality of our database and fetcher (specifically, if the database is set up correctly and if the books are fetched correctly).
If we don't stub, we do not know if the logic of highRatedBooks() works when the test fails as it could also come from a wrong state of the db or incorrect querying. We want to test BookManager in isolation to ensure its business rules work properly.
2. We need to inject dependencies to test it. So, I refactor the code by creating a field for the fetcher in the Bookmanager class and create a constructor. That way we can stub the field when testing and mock fetcher.
3. With our test suite we can now see that BookManager is implemented correctly. When we fetch a certain list from our Database, we can see that it is filtered correctly and the wanted books are returned.
However, with stubbing the dependency (the fetcher), we only know that bookManager works when it is isolated. We don't know if we get a correct data type (the list) from the fetcher or if the books are fetched and queried correctly from the database.
It could still be, that our program behaves incorrectly as a whole system, when for example not all books are fetched or when something in our database is wrong.
Still, we can say that if the behavior and querying from our database works (should be tested at another point), that the high rated books are correctly filtered and the function can handle various cases (null list, empty list etc.) as well as boundaries.

## B. Get list of all authors

I implemented the method similarly to the highRatedBooks method. Only change is, that I iterate through the list of books we get from the database and extract the author names.
Also, I added an author field and a getter for the field.
After the implementation, I proceeded with unit testing and considered following partitions:
- null list
- empty list
- list length one
- list length two

And the following interactions:
- no duplicate authors
- duplicate authors

SO, derived the following test suite:
- T1 null list
- T2 empty list
- T3 list length one
- T4 list length two, no duplicated
- T5 list length two, duplicates
- T6 additional value: list length 5, no duplicates
- T7 additional value: list length 5,duplicates

All the tests pass and I am happy with the implementation.