package com.hcmute.fit.project.instagram.domain.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public abstract class ResponseBaseAbstract {
    @JsonIgnore
    private HttpStatus statusCode;

    private String status;
    private Object data;
    private ArrayList<String> messages;

    public ResponseBaseAbstract() {
        this.statusCode = HttpStatus.OK;
        this.status = "OK";
        this.data = null;
        this.messages = new ArrayList<>();
    }

    public ResponseBaseAbstract(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ArrayList<String> getMessages() {
        return this.messages;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }
}
