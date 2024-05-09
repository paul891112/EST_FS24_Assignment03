# Cat facts

The class `CatFactsRetriever` communicates with a web API which returns random information about cats. The class has two methods:
- `retrieveRandom` that retrieves a random fact;
- `retrieveLongest(int limit)` fetches `limit` random facts, and returns the longest one (in terms of character count).

Write *unit tests* for these two methods, considering the following questions:
1. What are the external dependencies? Which of these dependencies should be tested using doubles and which should not? Explain your rationale.
2. For the dependencies that should be tested using doubles, what refactoring should be done in the code? **Do the refactoring and implement the tests**.
3. What are the disadvantages of using test doubles in your test suite? Answer with examples from the `CatFactsRetriever` class.

Automate the test cases using the **JUnit5** plugin in the `src/test/java/zest/` folder.

*Note 1*: Remember the techniques we learnt in the first chapters: test for empty cases, boundaries, etc. where applicable.

*Note 2*: Follow the **principles and best practices of good and maintainable test code**.


