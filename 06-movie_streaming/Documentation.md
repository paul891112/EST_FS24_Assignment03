# Documentation for MovieStreamingManagerTest

I implemented the required tests in `MovieStreamingManagerTest` class.

## Use of Doubles for FileStreamService and CacheService

I used Mockito to mock `FileStreamService` and `CacheService` objects for the tests.
This allowed me to isolate the `MovieStreamingManager` from its dependencies and test it in isolation.
This is required to avoid flaky tests, because using the real implementations would introduce problems if they don't work as expected.

## Mocking File System and Cache Interactions

My tests in `MovieStreamingManagerTest` assert that the `MovieStreamingManager` interacts correctly with the `FileStreamService` and `CacheService`.
1. `testStreamMovieCacheHit`: This test asserts that if the movie details are already in the cache, the `streamMovie` method retrieves them from there and does not interact with the `FileStreamService`.
2. `testStreamMovieCacheMissFileSystemHit`: This test asserts the scenario where the movie details are not in the cache but are available in the file system. It verifies that the `streamMovie` method retrieves the details from the file system, generates a streaming token, and caches the details for future use.
3. `testStreamMovieFileSystemFailure`: This test asserts that the `streamMovie` method correctly handles a situation where the `FileStreamService` fails when trying to retrieve a movie.
4. `testUpdateMovieMetadataSuccess`: This test asserts that the `updateMovieMetadata` method correctly updates the movie metadata in the file system and refreshes the cache.
5. `testUpdateMovieMetadataFileSystemFailure`: This test asserts that the `updateMovieMetadata` method correctly handles a situation where the `FileStreamService` fails when trying to update movie metadata.
6. `testUpdateMovieMetadataCacheFailure`: This test asserts that the `updateMovieMetadata` method correctly handles a situation where the `CacheService` fails when trying to refresh the cache.
7. `testValidateStreamingTokenValidToken`: This test asserts that the `validateStreamingToken` method correctly validates a valid streaming token.
8. `testValidateStreamingTokenInvalidToken`: This test asserts that the `validateStreamingToken` method correctly invalidates an invalid streaming token.
9. `testValidateStreamingTokenFileSystemFailure`: This test asserts that the `validateStreamingToken` method correctly handles a situation where the `FileStreamService` fails when trying to validate a streaming token.

## Handling of Failures

In my `MovieStreamingManagerTest` test class I included tests that simulate failures in the `FileStreamService` and `CacheService`. 
For example, in the `testStreamMovieFileSystemFailure` test, I simulated a situation where the `FileStreamService` throws an exception when trying to retrieve a movie. 
I verified that the `MovieStreamingManager` handles this situation correctly by throwing an exception.

Github Copilot was used to improve the format of the Documentation.md file. The prompt was "Reformat this file and highlight all file names and methods", including the Documentation.md file I wrote.