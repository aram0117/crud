package com.basic.crud.member.repository;

import com.basic.crud.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByIdAndIsDeletedFalse(Long memberId);

    List<Member> findAllByIsDeletedFalse();

    Optional<Member> findByEmail(String email);
}
