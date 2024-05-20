package zest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MovieStreamingManagerTest {

    private MovieStreamingManager movieStreamingManager;
    private FileStreamService fileStreamServiceMock;
    private CacheService cacheServiceMock;

    @BeforeEach
    public void setUp() {
        fileStreamServiceMock = mock(FileStreamService.class);
        cacheServiceMock = mock(CacheService.class);
        movieStreamingManager = new MovieStreamingManager(fileStreamServiceMock, cacheServiceMock);
    }

    @Test
    public void testStreamMovieCacheHit() {
        String movieId = "movie123";
        String expectedToken = "token123";
        MovieMetadata expectedMetadata = new MovieMetadata("Title", "Description");
        StreamingDetails expectedDetails = new StreamingDetails(movieId, expectedToken, expectedMetadata);

        when(cacheServiceMock.getDetails(movieId)).thenReturn(expectedDetails);

        StreamingDetails details = movieStreamingManager.streamMovie(movieId);

        assertEquals(expectedDetails, details);
        verify(cacheServiceMock).getDetails(movieId);
        verifyNoInteractions(fileStreamServiceMock);
    }

    @Test
    public void testStreamMovieCacheMissFileSystemHit() {
        String movieId = "movie123";
        String expectedToken = "token123";
        MovieMetadata expectedMetadata = new MovieMetadata("Title", "Description");
        StreamingDetails expectedDetails = new StreamingDetails(movieId, expectedToken, expectedMetadata);

        when(cacheServiceMock.getDetails(movieId)).thenReturn(null);
        when(fileStreamServiceMock.retrieveMovie(movieId)).thenReturn(expectedMetadata);
        when(fileStreamServiceMock.generateToken(movieId)).thenReturn(expectedToken);

        StreamingDetails details = movieStreamingManager.streamMovie(movieId);

        assertEquals(expectedDetails.getMovieId(), details.getMovieId());
        assertEquals(expectedDetails.getStreamToken(), details.getStreamToken());
        assertEquals(expectedDetails.getMetadata().getDescription(), details.getMetadata().getDescription());
        assertEquals(expectedDetails.getMetadata().getTitle(), details.getMetadata().getTitle());
        verify(cacheServiceMock).getDetails(movieId);
        verify(fileStreamServiceMock).retrieveMovie(movieId);
        verify(fileStreamServiceMock).generateToken(movieId);
        verify(cacheServiceMock).cacheDetails(movieId, expectedDetails);
    }

    @Test
    public void testStreamMovieFileSystemFailure() {
        String movieId = "movie123";

        when(cacheServiceMock.getDetails(movieId)).thenReturn(null);
        when(fileStreamServiceMock.retrieveMovie(movieId)).thenThrow(new RuntimeException("File system down"));

        assertThrows(RuntimeException.class, () -> movieStreamingManager.streamMovie(movieId));

        verify(cacheServiceMock).getDetails(movieId);
        verify(fileStreamServiceMock).retrieveMovie(movieId);
        verify(fileStreamServiceMock, never()).generateToken(movieId);
        verify(cacheServiceMock, never()).cacheDetails(anyString(), any(StreamingDetails.class));
    }
    @Test
    public void testUpdateMovieMetadataSuccess() {
        String movieId = "movie123";
        MovieMetadata metadata = new MovieMetadata("Updated Title", "Updated Description");

        doNothing().when(fileStreamServiceMock).updateMetadata(movieId, metadata);

        movieStreamingManager.updateMovieMetadata(movieId, metadata);

        verify(fileStreamServiceMock).updateMetadata(movieId, metadata);
        verify(cacheServiceMock).refreshCache(movieId, metadata);
    }

    @Test
    public void testUpdateMovieMetadataFileSystemFailure() {
        String movieId = "movie123";
        MovieMetadata metadata = new MovieMetadata("Updated Title", "Updated Description");

        doThrow(new RuntimeException("File system down")).when(fileStreamServiceMock).updateMetadata(movieId, metadata);

        assertThrows(RuntimeException.class, () -> movieStreamingManager.updateMovieMetadata(movieId, metadata));

        verify(fileStreamServiceMock).updateMetadata(movieId, metadata);
        verify(cacheServiceMock, never()).refreshCache(anyString(), any(MovieMetadata.class));
    }
    @Test
    public void testUpdateMovieMetadataCacheFailure() {
        String movieId = "movie123";
        MovieMetadata metadata = new MovieMetadata("Updated Title", "Updated Description");

        doThrow(new RuntimeException("File system down")).when(cacheServiceMock).refreshCache(movieId, metadata);

        assertThrows(RuntimeException.class, () -> movieStreamingManager.updateMovieMetadata(movieId, metadata));

        verify(fileStreamServiceMock).updateMetadata(movieId, metadata);
        verify(cacheServiceMock).refreshCache(movieId, metadata);
    }
    @Test
    public void testValidateStreamingTokenValidToken() {
        String token = "validToken";

        when(fileStreamServiceMock.validateToken(token)).thenReturn(true);

        boolean isValid = movieStreamingManager.validateStreamingToken(token);

        assertTrue(isValid);
        verify(fileStreamServiceMock).validateToken(token);
    }

    @Test
    public void testValidateStreamingTokenInvalidToken() {
        String token = "invalidToken";

        when(fileStreamServiceMock.validateToken(token)).thenReturn(false);

        boolean isValid = movieStreamingManager.validateStreamingToken(token);

        assertFalse(isValid);
        verify(fileStreamServiceMock).validateToken(token);
    }

    @Test
    public void testValidateStreamingTokenFileSystemFailure() {
        String token = "anyToken";

        when(fileStreamServiceMock.validateToken(token)).thenThrow(new RuntimeException("File system down"));

        assertThrows(RuntimeException.class, () -> movieStreamingManager.validateStreamingToken(token));

        verify(fileStreamServiceMock).validateToken(token);
    }

}
