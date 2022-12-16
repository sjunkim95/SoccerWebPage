package com.example.spring03.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring03.domain.SoccerPosts;
import com.example.spring03.dto.SoccerPostsCreateDto;
import com.example.spring03.dto.SoccerPostsUpdateDto;
import com.example.spring03.service.SoccerPostsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/view")
public class SoccerPostsController {

	private final SoccerPostsService soccerPostsService;

	// 리스트 목록
	@GetMapping("/list") // 요청 URL/방식 매핑.
	public String read(Model model,
			@PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.DESC) Pageable pageable) {
		log.info("read()");
		Page<SoccerPosts> list = soccerPostsService.read(pageable); // DB에서 포스트 목록 전체 검색.
		int nowPage = list.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, list.getTotalPages());
		model.addAttribute("list", list); // 뷰에 전달하는 모델 데이터.
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "/view/list";
		// View 이름 -> src/main/resources/templates/post/list.html

	}

	// 게시물 생성
	@GetMapping("/create")
	public void create() {
		log.info("create()");
	}

	@PostMapping("/create")
	public String create(String type, SoccerPostsCreateDto dto, RedirectAttributes attrs) {
		log.info("create(dto={}, type={})", dto, type);

		dto.setCategory(type);
		SoccerPosts entity = soccerPostsService.create(dto);
		attrs.addFlashAttribute("createdId", entity.getId());

		return "redirect:/view/list";
	}

	// 게시물 상세보기, 수정하기, 조회수 증가
	@GetMapping({ "/detail", "/modify" })
	public void detail(Integer id, Integer clickCount,Model model) {
		log.info("detail(id={})", id);
		log.info("modify(id={})", id);
		// 아이디 화면 불러오기
		SoccerPosts post = soccerPostsService.read(id);
		// 해당 아이디 조회수 추가
		soccerPostsService.clickCount(id);
		log.info("clickCount(id={}, clickCount ={})", id, post.getClickCount());
		model.addAttribute("post", post);
		
	}

	// 게시글 삭제하기
	@PostMapping("/delete")
	public String delete(Integer id, RedirectAttributes attrs) {
		log.info("delete(id= {})", id);

		Integer infoId = soccerPostsService.delete(id);
		System.out.println(infoId);
		attrs.addFlashAttribute("deletedInfoId", infoId);

		return "redirect:/view/list";
	}

	// 게시글 업데이트
	@PostMapping("/update")
	public String update(SoccerPostsUpdateDto dto) {
		log.info("update(dto={})", dto);

		Integer infoId = soccerPostsService.update(dto);

		return "redirect:/view/detail?id=" + dto.getId();
	}

	// 게시글 검색하기
	@GetMapping("/search")
	public String search(String type, String keyword,
			@PageableDefault(size = 5, sort = "id", direction = Direction.DESC) Pageable pageable, Model model) {
		log.info("search(type={}, keyword={})", type, keyword);

		Page<SoccerPosts> list = soccerPostsService.search(type, keyword, pageable);
		int nowPage = list.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 6, list.getTotalPages());

		model.addAttribute("list", list);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("isSearchPage", "true");
		model.addAttribute("type", type);
		model.addAttribute("keyword", keyword);

		return "/view/search";
	}

}
