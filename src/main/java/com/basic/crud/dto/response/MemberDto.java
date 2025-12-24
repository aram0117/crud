package com.basic.crud.dto.response;

import com.basic.crud.entity.Member;

import java.time.LocalDateTime;

public class MemberDto {

    private final Long id;
    private final String name;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public MemberDto(Long id, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public static MemberDto from(Member member) {
        return new MemberDto(
                member.getId(),
                member.getName(),
                member.getCreatedAt(),
                member.getUpdatedAt());
    }
}
