import java.util.List;

public class MessageProcessor {

    public void processMessages(List<Message> messages) {
        MessageService messageService = new MessageService();

        for (Message message : messages) {
            messageService.sendMessage(message.getReceiver(), message.getContent());
        }
    }
}
