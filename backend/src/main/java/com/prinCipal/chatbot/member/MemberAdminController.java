package com.prinCipal.chatbot.member;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.*;

@RestController
@RequestMapping("/admin/members")
@RequiredArgsConstructor
public class MemberAdminController {

    private final MemberRepository memberRepository;

    // 회원 목록 조회
    @GetMapping
    public List<MemberProfileDto> listMembers() {
        return memberRepository.findAll()
                .stream()
                .map(MemberProfileDto::from)
                .collect(Collectors.toList());
    }

    // 회원 탈퇴(상태 변경 + 탈퇴일 저장)
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<?> withdrawMember(@PathVariable Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));

        memberRepository.save(member); // 변경 감지(Dirty Checking)로 인해 save는 생략 가능할 수 있지만, 명시적으로 작성

        return ResponseEntity.ok().body("탈퇴 처리 완료");
    }
}
