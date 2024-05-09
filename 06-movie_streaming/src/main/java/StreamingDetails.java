public class StreamingDetails {
    private String movieId;
    private String streamToken;
    private MovieMetadata metadata;

    // Constructor
    public StreamingDetails(String movieId, String streamToken, MovieMetadata metadata) {
        this.movieId = movieId;
        this.streamToken = streamToken;
        this.metadata = metadata;
    }

    // Getters
    public String getMovieId() {
        return movieId;
    }

    public String getStreamToken() {
        return streamToken;
    }

    public MovieMetadata getMetadata() {
        return metadata;
    }

    // Setters
    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public void setStreamToken(String streamToken) {
        this.streamToken = streamToken;
    }

    public void setMetadata(MovieMetadata metadata) {
        this.metadata = metadata;
    }
}
