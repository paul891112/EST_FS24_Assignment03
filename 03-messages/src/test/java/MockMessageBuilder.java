public class MockMessageBuilder {

    private String sender = "sender";
    private String receiver = "receiver";
    private String content = "default content";

    public MockMessageBuilder withSender(String sender){
        this.sender = sender;
        return this;
    }

    public MockMessageBuilder withReceiver(String receiver){
        this.receiver = receiver;
        return this;
    }

    public MockMessageBuilder withContent(String content){
        this.content = content;
        return this;
    }

    public Message build(){
        return new Message(this.sender, this.receiver, this.content);
    }

}
