
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.InstanceOfAssertFactories.list;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class EventPublisherTest {

    @Mock EventListener eventListener;
    @Mock InventoryManager im;
    @Mock EmailNotificationService ens;

    @Test
    public void whenOneIM_OneInvocation(){
        EventPublisher eventPublisher = new EventPublisher();
        eventPublisher.subscribe(im);
        Order order = new MockOrderBuilder().build();

        doNothing().when(im).onOrderPlaced(any());
        eventPublisher.publishOrderToAllListeners(order);
        verify(im, times(1)).onOrderPlaced(any());
    }

    @Test
    public void whenOneEMS_OneInvocation(){
        EventPublisher eventPublisher = new EventPublisher();
        eventPublisher.subscribe(ens);
        Order order = new MockOrderBuilder().build();

        doNothing().when(ens).onOrderPlaced(any());
        eventPublisher.publishOrderToAllListeners(order);
        verify(ens, times(1)).onOrderPlaced(any());
    }

    @Test
    public void whenOneIMOneENS_TwoInvocation(){
        EventPublisher eventPublisher = new EventPublisher();
        eventPublisher.subscribe(im);
        eventPublisher.subscribe(ens);
        Order order = new MockOrderBuilder().build();
        assertThat(eventPublisher.getListeners().size()).isEqualTo(2);

        doNothing().when(im).onOrderPlaced(any());
        doNothing().when(ens).onOrderPlaced(any());
        eventPublisher.publishOrderToAllListeners(order);
        verify(im, times(1)).onOrderPlaced(any());
        verify(ens, times(1)).onOrderPlaced(any());

    }

    @Test
    public void whenFiveEvents_FiveInvocations(){
        EventPublisher eventPublisher = new EventPublisher();
        Order order = new MockOrderBuilder().build();
        for(int i=0;i<5;i++){
            eventPublisher.subscribe(eventListener);
        }
        eventPublisher.publishOrderToAllListeners(order);
        verify(eventListener,times(5)).onOrderPlaced(any());
    }

    @Test
    public void whenFiveEvents_AllArgumentsAreCorrectWithCaptor(){
        EventPublisher eventPublisher = new EventPublisher();
        Order order = new MockOrderBuilder().withOrderId("123").withAmount(3).build();
        for(int i=0;i<5;i++){
            eventPublisher.subscribe(eventListener);
        }
        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);

        eventPublisher.publishOrderToAllListeners(order);
        verify(eventListener,times(5)).onOrderPlaced(orderCaptor.capture());
        for (Order elem: orderCaptor.getAllValues()){
            assertThat(elem).isEqualTo(order);
        }
    }

    @Test
    public void whenThreeIMAndThreeENS_ArgumentsAreCorrectWithoutCaptor(){
        EventPublisher eventPublisher = new EventPublisher();
        Order order = new MockOrderBuilder().withOrderId("123").withAmount(3).build();
        for(int i=0;i<3;i++){
            eventPublisher.subscribe(im);
            eventPublisher.subscribe(ens);
        }
        List<String> results = eventPublisher.publishOrderToAllListeners(order);
        verify(im, times(3)).onOrderPlaced(any());
        verify(ens, times(3)).onOrderPlaced(any());

        for(String res: results){
            assertThat(res).isEqualTo(order.toString());
        }

    }

    @ParameterizedTest
    @CsvSource({
            "Aa1254837,5",
            "N126440828, 12",
            "naiue8203j, 30"
    })
    public void whenSeveralOrdersRandomEventListeners_OrderInfosAreCorrect(String id, double a){
        EventPublisher eventPublisher = new EventPublisher();
        doNothing().when(eventListener).onOrderPlaced(any());
        Order order = new MockOrderBuilder().withOrderId(id).withAmount(a).build();
        Random rand = new Random();
        int x = rand.nextInt(1000);
        for(int i=0; i<x; i++){
            eventPublisher.subscribe(eventListener);
        }
        List<String> results = eventPublisher.publishOrderToAllListeners(order);
        verify(eventListener, times(x)).onOrderPlaced(any());
        for(String elem: results){
            assertThat(elem).isEqualTo(order.toString());
        }
    }
}
