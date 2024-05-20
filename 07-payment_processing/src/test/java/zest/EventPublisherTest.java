package zest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventPublisherTest {

    @Test
    public void testEventPublisher() {
        // Create instances and mocks of the classes
        AuditService auditService = Mockito.mock(AuditService.class);
        Transaction transaction = Mockito.mock(Transaction.class);
        EventPublisher eventPublisher = new EventPublisher();
        eventPublisher.subscribe(auditService);

        // Call the method under test
        eventPublisher.publishTransactionComplete(transaction);

        // Assert the method was called the correct number of times
        Mockito.verify(auditService, Mockito.times(1)).onTransactionComplete(transaction);
    }

    @Test
    public void testEventPublisherWithArgumentCaptor() {
        // Arrange
        AuditService auditService = Mockito.mock(AuditService.class);
        Transaction transaction = Mockito.mock(Transaction.class);
        EventPublisher eventPublisher = new EventPublisher();
        eventPublisher.subscribe(auditService);

        // Act
        eventPublisher.publishTransactionComplete(transaction);

        // Assert
        ArgumentCaptor<Transaction> argumentCaptor = ArgumentCaptor.forClass(Transaction.class);
        Mockito.verify(auditService, Mockito.times(1)).onTransactionComplete(argumentCaptor.capture());
        assertEquals(transaction, argumentCaptor.getValue());
    }

}


