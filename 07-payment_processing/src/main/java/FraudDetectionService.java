package zest;

public interface FraudDetectionService {
    boolean evaluateTransaction(Transaction transaction);
}
