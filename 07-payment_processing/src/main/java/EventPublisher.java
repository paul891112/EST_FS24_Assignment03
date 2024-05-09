package zest;

import java.util.ArrayList;
import java.util.List;

public class EventPublisher {
    private List<AuditService> listeners = new ArrayList<>();

    public void subscribe(AuditService listener) {
        listeners.add(listener);
    }

    public void publishTransactionComplete(Transaction transaction) {
        for (AuditService listener : listeners) {
            listener.onTransactionComplete(transaction);
        }
    }
}
