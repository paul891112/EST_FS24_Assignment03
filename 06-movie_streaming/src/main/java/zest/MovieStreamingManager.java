package zest;

public class MovieStreamingManager {
    private FileStreamService fileStreamService;
    private CacheService cacheService;

    // Constructor to inject the file stream and cache services
    public MovieStreamingManager(FileStreamService fileStreamService, CacheService cacheService) {
        this.fileStreamService = fileStreamService;
        this.cacheService = cacheService;
    }

    // Method to stream a movie by its ID
    public StreamingDetails streamMovie(String movieId) {
        StreamingDetails details = cacheService.getDetails(movieId);
        if (details == null) {
            MovieMetadata metadata = fileStreamService.retrieveMovie(movieId);
            String streamToken = fileStreamService.generateToken(movieId);  // Assume there's a method to generate a streaming token
            details = new StreamingDetails(movieId, streamToken, metadata);
            cacheService.cacheDetails(movieId, details);
        }
        return details;
    }

    public void updateMovieMetadata(String movieId, MovieMetadata metadata){
        fileStreamService.updateMetadata(movieId, metadata);
        cacheService.refreshCache(movieId, metadata);
    } //Updates movie information in the distributed file system and refreshes the cache.

    public boolean validateStreamingToken(String token){
        return fileStreamService.validateToken(token);
    } //Checks the validity of a token against file system records.
}
