package com.basic.crud;

import javax.swing.plaf.PanelUI;

public class GlobalResponse<T> {

    // 속
    private final boolean success;
    private final int errorCode;
    private final String message;
    private final T data;

    // 생
    public GlobalResponse(boolean success, int errorCode, String message, T data) {
        this.success = success;
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }

    // 기
    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static <T> GlobalResponse<T> success(String message, int errorCode, T data) {
        return new GlobalResponse<>(true, errorCode, message, data);
    }

    public static GlobalResponse exception(String message, int errorCode) {
        return new GlobalResponse<>(false, errorCode, message, null);
    }

    public static <T> GlobalResponse<Void> successNoData(String message, int errorCode) {
        return new GlobalResponse<>(true, errorCode, message, null);
    }
}
