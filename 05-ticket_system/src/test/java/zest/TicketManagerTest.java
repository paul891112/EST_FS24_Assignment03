package zest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

public class TicketManagerTest {

    private TicketManager ticketManager;
    private NotificationService notificationServiceMock;
    private LogService logServiceMock;
    private TicketRepository ticketRepositoryMock;

    @BeforeEach
    public void setUp() {
        notificationServiceMock = mock(NotificationService.class);
        logServiceMock = mock(LogService.class);
        ticketRepositoryMock = mock(TicketRepository.class);
        ticketManager = new TicketManager(notificationServiceMock, logServiceMock, ticketRepositoryMock);
    }
    @Test
    public void testCreateTicket_Success() {
        Ticket ticket = new Ticket("customer@example.com", "Issue with software", TicketPriority.NORMAL);

        ticketManager.createTicket(ticket);

        verify(logServiceMock).logTicketCreation(ticket);
        verify(notificationServiceMock).notifyCustomer(eq("customer@example.com"), anyString());
        verify(ticketRepositoryMock).save(ticket);
    }

    @Test
    public void testCreateTicket_LoggingFailure() {
        Ticket ticket = new Ticket("customer@example.com", "Issue with software", TicketPriority.NORMAL);

        doThrow(new RuntimeException("Logging service down")).when(logServiceMock).logTicketCreation(ticket);

        assertDoesNotThrow(() -> ticketManager.createTicket(ticket));

        verify(logServiceMock).logTicketCreation(ticket);
        verify(notificationServiceMock).notifyCustomer(eq("customer@example.com"), anyString());
        verify(ticketRepositoryMock).save(ticket);
    }

    @Test
    public void testCreateTicket_NotificationFailure() {
        Ticket ticket = new Ticket("customer@example.com", "Issue with software", TicketPriority.NORMAL);

        doThrow(new RuntimeException("Notification service down")).when(notificationServiceMock).notifyCustomer(eq("customer@example.com"), anyString());

        assertDoesNotThrow(() -> ticketManager.createTicket(ticket));

        verify(logServiceMock).logTicketCreation(ticket);
        verify(notificationServiceMock).notifyCustomer(eq("customer@example.com"), anyString());
        verify(ticketRepositoryMock).save(ticket);
    }

    @Test
    public void testCreateTicket_BothServicesFailure() {
        Ticket ticket = new Ticket("customer@example.com", "Issue with software", TicketPriority.NORMAL);

        doThrow(new RuntimeException("Logging service down")).when(logServiceMock).logTicketCreation(ticket);
        doThrow(new RuntimeException("Notification service down")).when(notificationServiceMock).notifyCustomer(eq("customer@example.com"), anyString());

        assertDoesNotThrow(() -> ticketManager.createTicket(ticket));

        verify(logServiceMock).logTicketCreation(ticket);
        verify(notificationServiceMock).notifyCustomer(eq("customer@example.com"), anyString());
        verify(ticketRepositoryMock).save(ticket);
    }

    @Test
    public void testCreateTicket_NullCustomerEmail() {
        Ticket ticket = new Ticket(null, "Issue with software", TicketPriority.NORMAL);

        assertDoesNotThrow(() -> ticketManager.createTicket(ticket));

        verify(logServiceMock).logTicketCreation(ticket);
        verify(notificationServiceMock).notifyCustomer(eq(null), anyString());
        verify(ticketRepositoryMock).save(ticket);
    }

    @Test
    public void testCreateTicket_EmptyCustomerEmail() {
        Ticket ticket = new Ticket("", "Issue with software", TicketPriority.NORMAL);

        assertDoesNotThrow(() -> ticketManager.createTicket(ticket));

        verify(logServiceMock).logTicketCreation(ticket);
        verify(notificationServiceMock).notifyCustomer(eq(""), anyString());
        verify(ticketRepositoryMock).save(ticket);
    }

    @Test
    public void testCreateTicket_NullIssueDescription() {
        Ticket ticket = new Ticket("customer@example.com", null, TicketPriority.NORMAL);

        assertDoesNotThrow(() -> ticketManager.createTicket(ticket));

        verify(logServiceMock).logTicketCreation(ticket);
        verify(notificationServiceMock).notifyCustomer(eq("customer@example.com"), anyString());
        verify(ticketRepositoryMock).save(ticket);
    }

    @Test
    public void testCreateTicket_EmptyIssueDescription() {
        Ticket ticket = new Ticket("customer@example.com", "", TicketPriority.NORMAL);

        assertDoesNotThrow(() -> ticketManager.createTicket(ticket));

        verify(logServiceMock).logTicketCreation(ticket);
        verify(notificationServiceMock).notifyCustomer(eq("customer@example.com"), anyString());
        verify(ticketRepositoryMock).save(ticket);
    }
}
