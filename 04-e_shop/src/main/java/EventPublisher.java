import java.util.ArrayList;
import java.util.List;

public class EventPublisher {

    private List<EventListener> listeners = new ArrayList<>();

    public void subscribe(EventListener listener) {
        listeners.add(listener);
    }

    public List<EventListener> getListeners() { return this.listeners;}

    public List<String> publishOrderToAllListeners(Order order) {
        List<String> results = new ArrayList<>();
        for (EventListener listener : listeners) {
            listener.onOrderPlaced(order);
            results.add(order.toString());
        }
        return results;
    }
}
