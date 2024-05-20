package zest;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StreamingDetails that = (StreamingDetails) o;
        return Objects.equals(movieId, that.movieId) &&
                Objects.equals(streamToken, that.streamToken) &&
                Objects.equals(metadata, that.metadata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, streamToken, metadata);
    }
}
