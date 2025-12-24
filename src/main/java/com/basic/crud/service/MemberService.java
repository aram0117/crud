package com.basic.crud.service;

import com.basic.crud.dto.request.CreateMemberRequestDto;
import com.basic.crud.dto.request.UpdateMemberRequestDto;
import com.basic.crud.dto.response.*;
import com.basic.crud.entity.Member;
import com.basic.crud.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Transactional
    public CreateMemberResponseDto saveMember(CreateMemberRequestDto requestDto) {

        Member newMember = new Member(
                requestDto.getName()
        );

        Member saveMember = memberRepository.save(newMember);

        return CreateMemberResponseDto.from(MemberDto.from(saveMember));
    }

    @Transactional(readOnly = true)
    public GetDetailMemberResponseDto getDetailMember(Long memberId) {

        Member foundMember = memberRepository.findByIdAndIsDeletedFalse(memberId).orElseThrow(
                () -> new RuntimeException("member Not Found")
        );

        return GetDetailMemberResponseDto.from(MemberDto.from(foundMember));
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
                () -> new RuntimeException("member Not Found")
        );

        foundMember.updateMember(requestDto.getName());

        return new UpdateMemberResponseDto(
                foundMember.getId(),
                foundMember.getName(),
                LocalDateTime.now()
        );
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member foundmember = memberRepository.findById(memberId).orElseThrow(
                () -> new RuntimeException("member Not found")
        );

        if(foundmember.getIsDelete()) {
            throw new RuntimeException("member already deleted");
        }

        foundmember.deleteMember();
    }
}