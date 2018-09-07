package com.stackroute.tldm.model;

import java.util.Date;

public class MessageResponse {

    private String content;
    private String time;

    public MessageResponse() {
        super();
    }

    public MessageResponse(String content, String time) {
        this.content = content;
        this.time = time;
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
}
