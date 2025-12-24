package com.basic.crud.dto.response;


import java.time.LocalDateTime;

public class CreateMemberResponseDto {

    private final Long id;
    private final String name;
    private final LocalDateTime createdAt;

    public CreateMemberResponseDto(Long id, String name, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
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

    public static CreateMemberResponseDto from(MemberDto memberDto) {
        return new CreateMemberResponseDto(
                memberDto.getId(),
                memberDto.getName(),
                memberDto.getCreatedAt()
        );
    }
}
