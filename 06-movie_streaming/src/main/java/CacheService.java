public interface CacheService {
    StreamingDetails getDetails(String movieId);
    void cacheDetails(String movieId, StreamingDetails details);
    void refreshCache(String movieId, MovieMetadata metadata);
}
