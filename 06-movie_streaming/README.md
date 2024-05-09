# Movie Streaming Service

### **MovieStreamingManager** class
The MovieStreamingManager class manages operations for an online movie-streaming platform. It interacts with a distributed file system for storing and retrieving movie metadata and streaming tokens (`FileStreamService`). The system also integrates a local caching system to optimize metadata retrieval (`CacheService`).

### Methods of MovieStreamingManager
- streamMovie(String movieId): Retrieves a streaming token and metadata from either the cache or the file system.
- updateMovieMetadata(String movieId, MovieMetadata metadata): Updates movie information in the distributed file system and refreshes the cache.
- validateStreamingToken(String token): Checks the validity of a token against file system records.

## Unit Tests for the Methods in MovieStreamingManager Class
Write unit tests for the methods in `MovieStreamingManager` class, considering the following:
1. **Use of Doubles for FileStreamService and CacheService**
   - Identify external dependencies and implement tests using mocks to verify proper interactions.
2. **Mocking File System and Cache Interactions**
   - Test interactions with the mock file system and cache to ensure metadata and tokens are handled correctly.
3. **Handling of Failures**
   - Simulate file system downtimes and cache misses to test the system's resilience and error handling.

**Automate the test cases using the **JUnit5** plugin in the `src/test/java/zest/` folder.**

*Note 1*: Remember the techniques we learnt in the first chapters: test for empty cases, boundaries, etc. where applicable.

*Note 2*: Follow the **principles and best practices of good and maintainable test code**.

