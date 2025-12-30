package com.basic.crud.member.controller;

import com.basic.crud.GlobalResponse;
import com.basic.crud.auth.service.AuthService;
import com.basic.crud.member.dto.request.SignupRequestDto;
import com.basic.crud.member.dto.response.SignupResponseDto;
import com.basic.crud.member.dto.request.UpdateMemberRequestDto;
import com.basic.crud.member.dto.response.GetAllMemberResponseDto;
import com.basic.crud.member.dto.response.GetDetailMemberResponseDto;
import com.basic.crud.member.dto.response.UpdateMemberResponseDto;
import com.basic.crud.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    // 속
    private final MemberService memberService;
    private final AuthService authService;

    // 생
    public MemberController(MemberService memberService, AuthService authService) {
        this.memberService = memberService;
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<GlobalResponse<SignupResponseDto>> signup(@RequestBody SignupRequestDto requestDto) {
        SignupResponseDto result = memberService.signup(requestDto);
        return ResponseEntity.ok(GlobalResponse.success("회원가입 성공", 200, result));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<GlobalResponse<GetDetailMemberResponseDto>> getDetailMember(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable Long memberId) {
        authService.validateToken(bearerToken);
        GetDetailMemberResponseDto result = memberService.getDetailMember(memberId);
        return ResponseEntity.ok(GlobalResponse.success("멤버 단건조회 성공", 200, result));
    }

    @GetMapping
    public ResponseEntity<GlobalResponse<GetAllMemberResponseDto>> getAllMember(@RequestHeader("Authorization") String bearerToken) {
        authService.validateToken(bearerToken);
        GetAllMemberResponseDto result = memberService.getAllMember();
        return ResponseEntity.ok(GlobalResponse.success("멤버 다건조회 성공", 200, result));
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<GlobalResponse<UpdateMemberResponseDto>> updateMember(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable Long memberId,
            @RequestBody UpdateMemberRequestDto requestDto) {
        authService.validateToken(bearerToken);
        UpdateMemberResponseDto result = memberService.updateMember(memberId, requestDto);
        return ResponseEntity.ok(GlobalResponse.success("멤버 수정 성공", 200, result));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<GlobalResponse<Void>> deleteMember(
            @RequestHeader("Authorization") String bearerToken,
            Long memberId) {
        authService.validateToken(bearerToken);
        memberService.deleteMember(memberId);
        return ResponseEntity.ok(GlobalResponse.successNoData("멤버 삭제 성공", 204));
    }
}
