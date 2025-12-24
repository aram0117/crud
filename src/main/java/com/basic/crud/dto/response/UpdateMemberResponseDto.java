package com.basic.crud.dto.response;

import java.time.LocalDateTime;

public class UpdateMemberResponseDto {


    private final Long id;
    private final String name;
    private final LocalDateTime updatedAt;

    public UpdateMemberResponseDto(Long id, String name, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
