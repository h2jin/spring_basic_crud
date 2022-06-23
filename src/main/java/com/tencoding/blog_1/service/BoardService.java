package com.tencoding.blog_1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tencoding.blog_1.dto.BoardSaveRequestDto;
import com.tencoding.blog_1.model.Board;
import com.tencoding.blog_1.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Transactional
	public void 글쓰기(BoardSaveRequestDto dto) {
		Board boardEntity = BoardSaveRequestDto.toEntity(dto);
		boardRepository.save(boardEntity);
	}

	@Transactional
	public Page<Board> 글목록보기(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional
	public Board 글상세보기(int id) {
		Board board = boardRepository.mFindById(id).orElseThrow(() -> {
			return new RuntimeException("id 값이 잘못되었습니다."); //????오류처리 하는거 하기
		});
		// 더티 채킹 - 조회수 증가
		// 영속성 context에 1차 캐시로 담김. 여기 담겨있는 board의 카운트와 리턴해주어 컨트롤러로 가는 board의 카운트 달라짐.
		// transaction 처리해줘서 변경된 것으로 데이터베이스에 flush 처리
		board.setReadCount(board.getReadCount() + 1);
		return board;
	}
	
	@Transactional
	public void 글수정하기(BoardSaveRequestDto dto, int id) {
		Board boardEntity = boardRepository.mFindById(id).orElseThrow(() -> { // orElseGet(new Board) 해줘도 됨. 없으면 새로 만들어준다.
			return new RuntimeException("해당 글은 존재하지 않습니다.");
		});
		boardEntity.setTitle(dto.getTitle());
		boardEntity.setContent(dto.getContent());
//		boardRepository.save(boardEntity); 해줘도 됨. 알아서 업데이트 해줌
		// 트랜젝션 처리하기 -> 글수정하기() 메서드가 종료되는 시점에 더티체킹 발생
	}
	
	@Transactional
	public int 글삭제하기(int id) {
//		boardRepository.deleteById(id); // mysql에서도 없는 id 값을 넣는 경우 다 성공처리. -> 리턴해줄 필요가 없음
		return boardRepository.mDeleteById(id);
	}

}
