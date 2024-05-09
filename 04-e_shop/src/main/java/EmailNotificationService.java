public class EmailNotificationService implements EventListener {
    @Override
    public void onOrderPlaced(Order order) {
        // Logic to send email about the order would go here...
        System.out.println("Email sent for order " + order.getOrderId());
    }
}