# Book Reviews

### A. Get high-rated books
The class `BookManager` communicates with an SQL database when in production, which contains book metadata (title, author, and rating).
The `highRatedBooks` method of `BookManager` returns only the books with high rating (high is defined as >= 4/5).

Write *unit tests* for the `highRatedBooks` method of the `BookManager` class, considering the following questions:
1. What are the external dependencies? Which of these dependencies should be tested using doubles and which should not? Explain your rationale.
2. For the dependencies that should be tested using doubles, what refactoring should be done in the code? **Do the refactoring and implement the tests**.
3. What are the disadvantages of using test doubles in your test suite? Answer with examples from the `BookManager` class.

### B. Get list of all authors
You must implement a new feature. Specifically, you have to implement a `uniqueAuthors` method in the `BookManager` class that will return the authors of all the books in our SQL database. After you implement the method, test it following the steps 1.-3. above.

Automate the test cases using the **JUnit5** plugin in the `src/test/java/zest/` folder.

*Note 1*: Remember the techniques we learnt in the first chapters: test for empty cases, boundaries, etc. where applicable.

*Note 2*: Follow the **principles and best practices of good and maintainable test code**.

