package com.basic.crud.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;   // 회원 식별자

    @Column(name = "name", nullable = false, length = 20)
    private String name;   // 회원 이름

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;   // 생성일

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;   // 수정일

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;   // 삭제 여부

    protected Member() {}  // JPA 기본 생성자

    public Member(String name) {
        this.name = name;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
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

    public void updateMember(String name) {
        this.name = name;
    }

    public boolean getIsDelete() {
        return isDeleted;
    }

    public void deleteMember() {
        this.isDeleted = true;
    }
}