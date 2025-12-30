package com.basic.crud.member.dto.response;

public class SignupResponseDto {

    private final String bearerToken;

    public SignupResponseDto(String bearerToken) {
        this.bearerToken = bearerToken;
    }

    public String getBearerToken() {
        return bearerToken;
    }
}
