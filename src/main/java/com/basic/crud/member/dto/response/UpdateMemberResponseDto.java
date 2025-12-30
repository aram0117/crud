package com.basic.crud.member.dto.response;

public class UpdateMemberResponseDto {
    // 속
    public final MemberDto memberDto;

    // 생
    public UpdateMemberResponseDto(MemberDto memberDto) {
        this.memberDto = memberDto;
    }

    // 기
    public static UpdateMemberResponseDto from(MemberDto memberDto) {
        return new UpdateMemberResponseDto(memberDto);
    }



}
