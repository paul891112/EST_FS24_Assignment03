public interface FileStreamService {
    MovieMetadata retrieveMovie(String movieId);
    void updateMetadata(String movieId, MovieMetadata metadata);
    String generateToken(String movieId);
}
