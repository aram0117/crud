package com.basic.crud.auth.controller;

import com.basic.crud.GlobalResponse;
import com.basic.crud.auth.dto.request.LonginRequestDto;
import com.basic.crud.auth.dto.response.LoginResponseDto;
import com.basic.crud.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    // 속
    private final AuthService authService;

    // 생
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // 기
    @PostMapping("/login")
    public ResponseEntity<GlobalResponse<LoginResponseDto>> login(@RequestBody LonginRequestDto requestDto) {
        LoginResponseDto result = authService.login(requestDto);
        return ResponseEntity.ok(GlobalResponse.success("로그인 성공", 200, result));
    }
}
