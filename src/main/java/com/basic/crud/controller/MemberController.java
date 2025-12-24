package com.basic.crud.controller;

import com.basic.crud.GlobalResponse;
import com.basic.crud.dto.request.CreateMemberRequestDto;
import com.basic.crud.dto.request.UpdateMemberRequestDto;
import com.basic.crud.dto.response.CreateMemberResponseDto;
import com.basic.crud.dto.response.GetAllMemberResponseDto;
import com.basic.crud.dto.response.GetDetailMemberResponseDto;
import com.basic.crud.dto.response.UpdateMemberResponseDto;
import com.basic.crud.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    // 속
    private final MemberService memberService;

    // 생
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 기
    @PostMapping
    public ResponseEntity<GlobalResponse<CreateMemberResponseDto>> createMember(@RequestBody CreateMemberRequestDto request) {
        CreateMemberResponseDto result = memberService.saveMember(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(GlobalResponse.success("멤버 생성 성공", result));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<GlobalResponse<GetDetailMemberResponseDto>> getOneMember(@PathVariable Long memberId) {
        GetDetailMemberResponseDto result = memberService.getDetailMember(memberId);
        return ResponseEntity.ok(GlobalResponse.success("멤버 단건조회 성공", result));

    }

    @GetMapping
    public ResponseEntity<GlobalResponse<GetAllMemberResponseDto>> getAllMember() {
        GetAllMemberResponseDto result = memberService.getAllMember();
        return ResponseEntity.ok(GlobalResponse.success("멤버 다건조회 성공", result));
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<GlobalResponse<UpdateMemberResponseDto>> updateMember(
            @PathVariable Long memberId,
            @RequestBody UpdateMemberRequestDto requestDto) {
        UpdateMemberResponseDto result = memberService.updateMember(memberId, requestDto);
        return ResponseEntity.ok(GlobalResponse.success("멤버 수정 성공", result));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<GlobalResponse<Void>> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.ok(GlobalResponse.successNoDate("멤버 삭제 성공"));
    }
}
