package com.basic.crud.auth.dto.response;

public class LoginResponseDto {
    // 속
    private final String bearerToken;

    // 생
    public LoginResponseDto(String bearerToken) {
        this.bearerToken = bearerToken;
    }

    public String getBearerToken() {
        return bearerToken;
    }
}
