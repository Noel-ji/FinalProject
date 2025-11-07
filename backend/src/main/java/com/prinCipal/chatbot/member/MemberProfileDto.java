package com.prinCipal.chatbot.member;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberProfileDto {
    
    private Long id;
    private String email;
    private String nickname;
    private String status;
    private LocalDateTime withdrawDate;

    @Builder
    private MemberProfileDto(Long id, String email, String nickname, String status, LocalDateTime withdrawDate) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.status = status;
        this.withdrawDate = withdrawDate;
    }

    public static MemberProfileDto from(Member member) {
        return MemberProfileDto.builder()
                .id(member.getUserId())
                .nickname(member.getNickname())
                .status(member.getStatus())
                .withdrawDate(member.getWithdrawDate())
                .build();
    }
}