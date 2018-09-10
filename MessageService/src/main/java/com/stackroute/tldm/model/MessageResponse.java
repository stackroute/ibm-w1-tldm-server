package com.stackroute.tldm.model;

public class MessageResponse {

    private String content;
    private String time;
//    private String sender_id;

    public MessageResponse() {
        super();
    }

    public MessageResponse(String content, String time) {
        this.content = content;
        this.time = time;
//        this.sender_id = sender_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

//    public String getSender_id() {
//        return sender_id;
//    }
//
//    public void setSender_id(String sender_id) {
//        this.sender_id = sender_id;
//    }
}
