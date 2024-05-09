public class InventoryManager implements EventListener {
    @Override
    public void onOrderPlaced(Order order) {
        // Logic to update inventory based on order would go here
        System.out.println("Inventory updated for order " + order.getOrderId());
    }
}