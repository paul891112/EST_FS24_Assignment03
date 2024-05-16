import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class MessageProcessorTest {

    @Mock
    private static MessageService messageService;
    private MessageProcessor messageProcessor = new MessageProcessor();
    private List<Message> args;


    @BeforeEach
    public void setUp(){
        messageService = mock(MessageService.class);
        doNothing().when(messageService).sendMessage(anyString(), anyString());
        args = new ArrayList<>();
    }

    @Test
    public void whenPassingOneElementList_CallOneTime(){
        args.add(new MockMessageBuilder().build());
        messageProcessor.processMessages(args, messageService);
        verify(messageService, times(1)).sendMessage(anyString(), anyString());
    }

    @Test
    public void whenPassingEmptyList_CallZeroTime(){
        messageProcessor.processMessages(args, messageService);
        verify(messageService, never()).sendMessage(anyString(), anyString());
    }

    @Test
    public void whenPassingFiveElementList_CallFiveTimes(){
        for(int i=0;i<5;i++){
            args.add(new MockMessageBuilder().withReceiver("rec" + i).withContent("Message to " + i).build());
        }
        messageProcessor.processMessages(args, messageService);
        verify(messageService, times(5)).sendMessage(anyString(), anyString());
    }

    @ParameterizedTest
    @ValueSource(ints = {8,17,55,234,779,1903})
    public void whenRandomSizeListX_CallXTime(int x){

        Random rand = new Random();
        for (int i=0; i<x;i++){
            args.add(new MockMessageBuilder().withReceiver("rec" + i).withContent("Message to " + i).build());
        }
        messageProcessor.processMessages(args, messageService);
        verify(messageService, times(x)).sendMessage(anyString(), anyString());
    }

    @ParameterizedTest
    @CsvSource({
            "Brady,Evans,Touchdown",
            "Rodgers,Moore,Drop",
            "Mahomes,Hill,Overthrown",
            "Paul,Curry,3Pointer"
    })
    public void whenSingleMessage_CorrectMessageInfo(String sender, String receiver, String content){

        Message m = new MockMessageBuilder().withSender(sender).withReceiver(receiver).withContent(content).build();
        args.add(m);
        messageProcessor.processMessages(args, messageService);
        ArgumentCaptor<String> receiverCaptor =
                ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> contentCaptor =
                ArgumentCaptor.forClass(String.class);
        verify(messageService).sendMessage(receiverCaptor.capture(), contentCaptor.capture());
        assertThat(receiverCaptor.getValue()).isEqualTo(receiver);
        assertThat(contentCaptor.getValue()).isEqualTo(content);
    }

    @Test
    public void whenListOfFiveMessages_MessagesAreCorrect(){
        List<String> receivers = new ArrayList<>();
        List<String> contents = new ArrayList<>();
        for(int i=0;i<5;i++){
            Message m = new MockMessageBuilder().withSender("Sender 1").withReceiver("rec" + i).withContent("Message to " + i).build();
            args.add(m);
            receivers.add(m.getReceiver());
            contents.add(m.getContent());
        }

        messageProcessor.processMessages(args, messageService);
        ArgumentCaptor<String> receiverCaptor =
                ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> contentCaptor =
                ArgumentCaptor.forClass(String.class);
        verify(messageService, times(5)).sendMessage(receiverCaptor.capture(), contentCaptor.capture());
        assertThat(receiverCaptor.getAllValues()).containsExactlyElementsOf(receivers);
        assertThat(contentCaptor.getAllValues()).containsExactlyElementsOf(contents);
    }

    @Test
    public void whenListOfTenMessages_CorrectWithoutArgumentCaptor() {
        List<String> recContents = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Message m = new MockMessageBuilder().withSender("Sender").withReceiver("rec" + i).withContent("Message to " + i).build();
            args.add(m);
            recContents.add(m.getReceiver() + ":" + m.getContent());
        }

        List<String> sentMessages = messageProcessor.processMessages(args, messageService);
        verify(messageService, times(10)).sendMessage(anyString(), anyString());
        assertThat(sentMessages).containsExactlyElementsOf(recContents);
    }

    @Test
    public void whenEmtpyList_NoMessageSent_WithoutArgumentCaptor(){
        List<String> sentMessages = messageProcessor.processMessages(args, messageService);
        verify(messageService, never()).sendMessage(anyString(), anyString());
        assertThat(sentMessages).isEmpty();
    }
}