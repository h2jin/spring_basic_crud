package com.tencoding.blog_1.dto;

import com.tencoding.blog_1.model.Board;

import lombok.Data;

@Data
public class BoardSaveRequestDto {
	// dto와 entity 구분할 것임.
	private String title;
	private String content;
	
	// 한단계 업그레이드
	public static Board toEntity(BoardSaveRequestDto dto) {
		Board boardEntity = Board.builder()
				.title(dto.getTitle())
				.content(dto.getContent())
				.build();
		return boardEntity;
	}

}
