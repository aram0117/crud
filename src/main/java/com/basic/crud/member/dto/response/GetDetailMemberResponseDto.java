package com.basic.crud.member.dto.response;

public class GetDetailMemberResponseDto {
    // 속
    private final MemberDto memberDto;

    // 생
    public GetDetailMemberResponseDto(MemberDto memberDto) {
        this.memberDto = memberDto;
    }

    // 기
    public MemberDto getMemberDto() {
        return memberDto;
    }

    public static GetDetailMemberResponseDto from(MemberDto memberDto) {
        return new GetDetailMemberResponseDto(memberDto);
    }
}
