package com.otio.backend.controller;

import java.time.LocalDateTime;

public class PayloadOutput<T> {
    private LocalDateTime time;
    private String status;
    private T data;
    
    public PayloadOutput() {}

    public PayloadOutput(LocalDateTime time, String status, T data) {
        this.time = time;
        this.status = status;
        this.data = data;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
