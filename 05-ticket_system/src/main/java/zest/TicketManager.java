package zest;

// TicketManager class to handle ticket creation and interaction with services
public class TicketManager {
    private NotificationService notificationService;
    private LogService logService;
    private TicketRepository ticketRepository;

    public TicketManager(NotificationService notificationService, LogService logService, TicketRepository ticketRepository) {
        this.notificationService = notificationService;
        this.logService = logService;
        this.ticketRepository = ticketRepository;
    }

    public void createTicket(Ticket ticket) {
        // Try to log the ticket creation
        try {
            logService.logTicketCreation(ticket);
        } catch (RuntimeException e) {
            System.err.println("Logging service failed: " + e.getMessage());
        }

        // Try to notify the customer
        try {
            notificationService.notifyCustomer(ticket.getCustomerEmail(),
                    "Thank you for your request. Your support ticket has been created and will be processed shortly.");
        } catch (RuntimeException e) {
            System.err.println("Notification service failed: " + e.getMessage());
        }

        // Save the ticket to the database
        saveTicket(ticket);
    }

    // Method to save ticket to a database
    private void saveTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }
}
