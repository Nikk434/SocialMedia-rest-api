package com.example.rest.webserivces.restful_web_services.Exceptions;

import java.time.LocalDate;

public class ErrorDetails  {
    private LocalDate timestamp;
    private String msg;
    private String details;
    public ErrorDetails(LocalDate timestamp, String msg, String details) {
        this.timestamp = timestamp;
        this.msg = msg;
        this.details = details;
    }
    public LocalDate getTimestamp() {
        return timestamp;
    }
    public String getMsg() {
        return msg;
    }
    public String getDetails() {
        return details;
    }

    
}
