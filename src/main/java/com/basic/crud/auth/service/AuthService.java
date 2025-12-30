package com.basic.crud.auth.service;

import com.basic.crud.auth.dto.request.LonginRequestDto;
import com.basic.crud.auth.dto.response.LoginResponseDto;
import com.basic.crud.member.entity.Member;
import com.basic.crud.member.repository.MemberRepository;
import com.basic.crud.utill.JwtUtil;
import com.basic.crud.utill.PasswordEncoder;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    // 속
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 생
    public AuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // 기
    public LoginResponseDto login(LonginRequestDto requestDto) {

        Member foundMember = memberRepository.findByEmail(requestDto.getEmail()).orElseThrow(
                () -> new RuntimeException("아이디를 찾을수 없습니다")
        );

        if (!passwordEncoder.matches(requestDto.getPassword(), foundMember.getPassword())) {
            throw new RuntimeException("비밀번호를 찾을수 없습니다");
        }

        String bearerToken = jwtUtil.generationToken(foundMember.getId(), foundMember.getEmail());

        return new LoginResponseDto(bearerToken);
    }

    public void validateToken(String bearerToken) {
        if (bearerToken == null || bearerToken.isBlank()) {
            throw new RuntimeException("토큰이 존재하지 않습니다");
        }

        if (!bearerToken.startsWith("Bearer ")) {
            throw new RuntimeException("잘못된 토큰 입니다");
        }

        String jwt = bearerToken.substring(7).trim();

        Claims claims = jwtUtil.extractToken(jwt);

        if (claims == null) {
            throw new RuntimeException("잘못된 토큰 입니다");
        }
    }
}

