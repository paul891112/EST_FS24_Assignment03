import java.util.ArrayList;
import java.util.List;

public class MessageProcessor {

    public List<String> processMessages(List<Message> messages, MessageService messageService) {
        List<String> sentMessages = new ArrayList<>();

        for (Message message : messages) {
            messageService.sendMessage(message.getReceiver(), message.getContent());
            String m = message.getReceiver() + ":" + message.getContent();
            sentMessages.add(m);
        }
        return sentMessages;
    }
}
