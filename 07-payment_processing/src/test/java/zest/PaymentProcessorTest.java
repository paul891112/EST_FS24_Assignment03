package zest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentProcessorTest {

    @Test
    public void testPaymentProcessorObservability() {
        // Create instances and mocks of the classes
        EventPublisher eventPublisher = Mockito.mock(EventPublisher.class);
        TransactionService transactionService = Mockito.mock(TransactionService.class);
        FraudDetectionService fraudDetectionService = Mockito.mock(FraudDetectionService.class);
        Transaction transaction = Mockito.mock(Transaction.class);
        PaymentProcessor paymentProcessor = new PaymentProcessor(eventPublisher, transactionService, fraudDetectionService);

        // Specify that evaluateTransaction should return true when called with transaction
        Mockito.when(fraudDetectionService.evaluateTransaction(transaction)).thenReturn(true);

        // Call the method under test
        paymentProcessor.processPayment(transaction);

        // Assure the method works as expected
        assertEquals(transaction, paymentProcessor.getLastProcessedTransaction());
    }

}

