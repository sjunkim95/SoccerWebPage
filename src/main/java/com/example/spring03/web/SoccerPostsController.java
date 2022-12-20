package com.example.spring03.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring03.domain.SoccerPosts;
import com.example.spring03.dto.FileDto;
import com.example.spring03.dto.SoccerPostsCreateDto;
import com.example.spring03.dto.SoccerPostsUpdateDto;
import com.example.spring03.service.FileService;
import com.example.spring03.service.SoccerPostsService;
import com.example.spring03.util.MD5Generator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/view")
public class SoccerPostsController {

	private final SoccerPostsService soccerPostsService;
	private final FileService fileService;

	// 리스트 목록
	@GetMapping("/list") // 요청 URL/방식 매핑.
	public String read(Model model, String category,
			@PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.DESC) Pageable pageable) {
		log.info("read(category = {})", category);
		Page<SoccerPosts> list = soccerPostsService.read(pageable, category); // DB에서 포스트 목록 전체 검색.
		int nowPage = list.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, list.getTotalPages());
		model.addAttribute("list", list); // 뷰에 전달하는 모델 데이터.
		model.addAttribute("category", category);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "/view/list";
		// View 이름 -> src/main/resources/templates/post/list.html

	}

	// 게시물 생성
	@GetMapping("/create")
    public String create(Model model, String category) {
        log.info("create(category = {})", category);
        model.addAttribute("category", category);
        
        log.info("home()");

        final String soccerUrl = "https://www.donga.com/ISSUE/2022WorldCup";
        Connection conn = Jsoup.connect(soccerUrl);
        
        
        try {
            Document document = conn.get();
            Elements fixutreElements = document.select("div.tab_con02 > img");
            
            for (int j = 0; j < fixutreElements.size(); j++) {
                final String url = fixutreElements.get(j).attr("abs:src");
                System.out.println(url);

                model.addAttribute("newsFixture", url);
            }
            
            final String soccerUrl2 = "https://www.donga.com/news/Issue/051011";
            Connection conn2 = Jsoup.connect(soccerUrl2);
            Document document2 = conn2.get();
            
            Elements titleElements = document2.select("span.tit");
            
            for (int j = 0; j < titleElements.size(); j++) {
                final String url = titleElements.get(j).text();
                
                
                model.addAttribute("newsTitle1", titleElements.get(1));
                model.addAttribute("newsTitle2", titleElements.get(2));
                model.addAttribute("newsTitle3", titleElements.get(3));
                model.addAttribute("newsTitle4", titleElements.get(4));
                
            }
            
          Elements imgElements = document2.select("div.articleList > div.thumb > a > img");
          
          for (int j = 0; j < imgElements.size(); j++) {
             //   final String url = imgElements.get(j).attr("abs:src");
                model.addAttribute("newsImg1", imgElements.get(1).attr("abs:src"));
                model.addAttribute("newsImg2", imgElements.get(2).attr("abs:src"));
                model.addAttribute("newsImg3", imgElements.get(3).attr("abs:src"));
                model.addAttribute("newsImg4", imgElements.get(4).attr("abs:src"));
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return "view/create";
    }

	@PostMapping("/create")
    public String create(@RequestParam("file") MultipartFile files, String type, SoccerPostsCreateDto dto, RedirectAttributes attrs) {
        log.info("create(dto={}, type = {})", dto, type);
        
        
        
         try {
                String origFilename = files.getOriginalFilename();
                if(origFilename.equals("")) {
                    dto.setCategory(type);
                    SoccerPosts entity = soccerPostsService.create(dto);
                    attrs.addFlashAttribute("createdId", entity.getId());
                    
                    return "redirect:/view/list?category=" + type;
                }
                
                String filename = new MD5Generator(origFilename).toString();
                /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
                String savePath = System.getProperty("user.dir") + "\\files";
                /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
                
                File Folder = new File(savePath);
                if (!Folder.exists()) {
                    try{
                        Folder.mkdir();
                    }
                    catch(Exception e){
                        e.getStackTrace();
                    }
                }
                
                String filePath = savePath + "\\" + filename;
                files.transferTo(new File(filePath));

                FileDto fileDto = new FileDto();
                fileDto.setOrigFilename(origFilename);
                fileDto.setFilename(filename);
                fileDto.setFilePath(filePath);
                
                Long fileId = fileService.saveFile(fileDto);
                        
                dto.setCategory(type);
                dto.setFilesId(fileId);
                SoccerPosts entity = soccerPostsService.create(dto);
                attrs.addFlashAttribute("createdId", entity.getId());
                        
                
            } catch(Exception e) {
                e.printStackTrace();
            }
        
         return "redirect:/view/list?category=" + type;
    }

	// 게시물 상세보기, 수정하기, 조회수 증가
	@GetMapping({ "/detail", "/modify" })
    public void detail(Integer id, Model model, Integer clickCount) {
        log.info("detail(id={})", id);
        log.info("modify(id={})", id);

        SoccerPosts post = soccerPostsService.read(id);
        soccerPostsService.clickCount(id);
        log.info("clickCount(id={}, clickCount ={})", id, post.getClickCount());
        
        Long fileId = post.getFilesId();
        
        if(!(fileId == null)) {
        String filename = fileService.getFile(fileId).getOrigFilename();
        log.info(filename);
        
        model.addAttribute("filename", filename);
        
        }
        
        model.addAttribute("post", post);
        
    }

	// 게시글 삭제하기
	@PostMapping("/delete")
	public String delete(Integer id, String category, RedirectAttributes attrs) {
		log.info("delete(id= {}, category = {})", id, category);

		Integer postId = soccerPostsService.delete(id);
		System.out.println(postId);
		attrs.addFlashAttribute("deletedId", postId);

		return "redirect:/view/list?category=" + category;
	}

	// 게시글 업데이트
	@PostMapping("/update")
	public String update(SoccerPostsUpdateDto dto, RedirectAttributes attrs) {
		log.info("update(dto={})", dto);

		Integer postId = soccerPostsService.update(dto);
		attrs.addFlashAttribute("updatedId", postId);
		
		return "redirect:/view/detail?id=" + postId;
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
