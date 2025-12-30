package com.basic.crud.member.dto.request;

public class SignupRequestDto {
    // 속
    private String name;
    private String email;
    private String password;


    //기
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
