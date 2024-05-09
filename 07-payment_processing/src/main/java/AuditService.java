package zest;

public interface AuditService {
    void onTransactionComplete(Transaction transaction);
}
