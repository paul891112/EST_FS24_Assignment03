package support;

import java.util.UUID;

public class Ticket {
    private String id;
    private String customerEmail;
    private String issueDescription;
    private TicketPriority priority;

    public Ticket(String customerEmail, String issueDescription, TicketPriority priority) {
        this.id = UUID.randomUUID().toString();
        this.customerEmail = customerEmail;
        this.issueDescription = issueDescription;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public TicketPriority getPriority() {
        return priority;
    }
}

enum TicketPriority {
    NORMAL,
    URGENT
}
