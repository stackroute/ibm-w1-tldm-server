package com.stackroute.tldm.model;

// The response client will receive in the front-end.

public class MessageResponse {

    private String content;
    private Sender sender;
    private Receiver receiver;
    private String time;

    public MessageResponse() {
        super();
    }

    public MessageResponse(String content, Sender sender, Receiver receiver, String time) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MessageResponse{" +
                "content='" + content + '\'' +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", time='" + time + '\'' +
                '}';
    }
}
