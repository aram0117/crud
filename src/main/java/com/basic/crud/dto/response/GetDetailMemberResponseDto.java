package com.basic.crud.dto.response;

import java.time.LocalDateTime;

public class GetDetailMemberResponseDto {


    private final Long id;
    private final String name;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public GetDetailMemberResponseDto(Long id, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public static GetDetailMemberResponseDto from(MemberDto memberDto) {
        return new GetDetailMemberResponseDto(
                memberDto.getId(),
                memberDto.getName(),
                memberDto.getCreatedAt(),
                memberDto.getUpdatedAt()
        );
    }
}
