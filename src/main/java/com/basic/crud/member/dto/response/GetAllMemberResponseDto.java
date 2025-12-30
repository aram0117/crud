package com.basic.crud.member.dto.response;

import java.util.List;

public class GetAllMemberResponseDto {

    private final List<MemberDto> memberDtoList;

    public GetAllMemberResponseDto(List<MemberDto> memberDtoList) {
        this.memberDtoList = memberDtoList;
    }

    public List<MemberDto> getMemberDtoList() {
        return memberDtoList;
    }

    public static GetAllMemberResponseDto from(List<MemberDto> memberDtoList) {
        return new GetAllMemberResponseDto(memberDtoList);
    }
}
