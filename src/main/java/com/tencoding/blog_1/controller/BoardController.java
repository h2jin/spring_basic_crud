package com.tencoding.blog_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tencoding.blog_1.dto.BoardSaveRequestDto;
import com.tencoding.blog_1.model.Board;
import com.tencoding.blog_1.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	// http://localhost:9090/list/?page=0
	@GetMapping({"", "/", "list"})
	public String list(@PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable, Model model) {
		// 서비스 객체에 접근해서 목록 가져와야 한다.
		Page<Board> boards = boardService.글목록보기(pageable);
		model.addAttribute("boards", boards);
		
		return "list";
	}
	
	@GetMapping({"/listpage"})
	@ResponseBody
	public Page<Board> listPage(@PageableDefault(size = 1, sort = "id", direction = Direction.DESC) Pageable pageable) {
		// 서비스 객체에 접근해서 목록 가져와야 한다.
		Page<Board> boards = boardService.글목록보기(pageable);
		return boards;
	}
	
	
	// 페이지 요청
	@GetMapping("/saveForm")
	public String saveForm() {
		return "saveForm";
	}
	
	// 저장 동작
	@PostMapping("/save")
	@ResponseBody
	public String save(@RequestBody BoardSaveRequestDto requestDto) { // title, content
		// 서비스 객체로 가서 DB 저장 요청
		boardService.글쓰기(requestDto);
		return "ok";
	}
	
	//하이퍼링크는 무조건 getMapping
	@GetMapping("/board/{id}")
	public String detail(@PathVariable int id, Model model) {
		// 서비스에 가서 데이터 가져오기
		model.addAttribute("board", boardService.글상세보기(id));
		return "detail";
	}
	
}
