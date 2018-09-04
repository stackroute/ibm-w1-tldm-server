package com.stackroute.tldm.model;

public class MessageResponse {

    private String content;

    public MessageResponse() {
        super();
    }

    public MessageResponse(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
