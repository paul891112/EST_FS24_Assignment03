package zest;
public interface FileStreamService {
    MovieMetadata retrieveMovie(String movieId);
    boolean validateToken(String token);
    void updateMetadata(String movieId, MovieMetadata metadata);
    String generateToken(String movieId);
}
