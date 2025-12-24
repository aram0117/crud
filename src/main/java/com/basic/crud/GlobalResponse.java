package com.basic.crud;

import java.util.List;

public class GlobalResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public GlobalResponse (boolean success, String message, T data){
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static <T> GlobalResponse<T> success(String message, T data) {
        return new GlobalResponse<>(true, message, data);
    }

    public static <T> GlobalResponse<Void> successNoDate(String message) {
        return new GlobalResponse<>(true, message, null);
    }
}
