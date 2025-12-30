package com.basic.crud.member.service;

import com.basic.crud.member.dto.request.SignupRequestDto;
import com.basic.crud.member.dto.request.UpdateMemberRequestDto;
import com.basic.crud.member.dto.response.*;
import com.basic.crud.member.entity.Member;
import com.basic.crud.member.repository.MemberRepository;
import com.basic.crud.utill.JwtUtil;
import com.basic.crud.utill.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {
    // 속
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    // 생
    public MemberService(MemberRepository memberRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    // 기
    @Transactional
    public SignupResponseDto signup(SignupRequestDto requestDto) {
        Member authMember = new Member(
                requestDto.getName(),
                requestDto.getEmail(),
                passwordEncoder.encode(requestDto.getPassword())
        );

        memberRepository.save(authMember);

        String bearerToken = jwtUtil.generationToken(authMember.getId(), authMember.getEmail());

        return new SignupResponseDto(bearerToken);
    }


    @Transactional(readOnly = true)
    public GetDetailMemberResponseDto getDetailMember(Long memberId) {
        Member foundmember = memberRepository.findByIdAndIsDeletedFalse(memberId).orElseThrow(
                () -> new RuntimeException("멤버를 찾을수 없음")
        );

        return GetDetailMemberResponseDto.from(MemberDto.from(foundmember));
    }

    @Transactional(readOnly = true)
    public GetAllMemberResponseDto getAllMember() {
        List<Member> foundMember = memberRepository.findAllByIsDeletedFalse();

        List<MemberDto> memberDtoList = foundMember.stream()
                .map(MemberDto::from)
                .toList();

        return GetAllMemberResponseDto.from(memberDtoList);
    }

    @Transactional
    public UpdateMemberResponseDto updateMember(Long memberId, UpdateMemberRequestDto requestDto) {
        Member foundMember = memberRepository.findByIdAndIsDeletedFalse(memberId).orElseThrow(
                () -> new RuntimeException("멤버를 찾을수 없음")
        );

        foundMember.updateMember(requestDto.getName());

        return UpdateMemberResponseDto.from(MemberDto.from(foundMember));
    }

    @Transactional("")
    public void deleteMember(Long memberId) {
        Member foundmember = memberRepository.findByIdAndIsDeletedFalse(memberId).orElseThrow(
                () -> new RuntimeException("멤버를 찾을수 없음")
        );

        if (foundmember.getIsDelete()) {
            throw new RuntimeException("이미 삭제된 멤버");
        }

        foundmember.deleteMember();
    }
}

