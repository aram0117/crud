package com.basic.crud.repository;

import com.basic.crud.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByIdAndIsDeletedFalse(Long Id);

    List<Member> findAllByIsDeletedFalse();
}
