package com.efub.community.domain.member.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdateRequestDto {
	@NotBlank(message = "닉네임은 필수값입니다. ")
	private String nickname;

	public MemberUpdateRequestDto(String nickname) {
		this.nickname = nickname;
	}
}