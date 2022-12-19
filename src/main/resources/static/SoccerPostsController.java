package com.example.spring03.web;

import java.util.List;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

	@GetMapping("/list") // 요청 URL/방식 매핑.	
	public String read(Model model, String category) {
		log.info("read(category = {})", category);
		
		List<SoccerPosts> list = soccerPostsService.read(category); // DB에서 포스트 목록 전체 검색.
		model.addAttribute("list", list); // 뷰에 전달하는 모델 데이터.
		model.addAttribute("category", category);
		
		return "/view/list";
	}
	
	@GetMapping("/create")
	public void create(Model model, String category) {
		log.info("create(category = {})", category);
		
		model.addAttribute("category", category);
	}

	@PostMapping("/create")
	public String create(@RequestParam("file") MultipartFile files, String type, SoccerPostsCreateDto dto, RedirectAttributes attrs) {
		log.info("create(dto={}, type = {})", dto, type);
		
		 try {
	            String origFilename = files.getOriginalFilename();
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

	@GetMapping({ "/detail", "/modify" })
	public void detail(Integer id, Model model) {
		log.info("detail(id={})", id);
		log.info("modify(id={})", id);

		SoccerPosts post = soccerPostsService.read(id);
		Long fileId = post.getFilesId();
		
		if(!(fileId == null)) {
		String filename = fileService.getFile(fileId).getOrigFilename();
		log.info(filename);
		
		model.addAttribute("filename", filename);
		
		}
		
		model.addAttribute("post", post);
		
	}

	@PostMapping("/delete")
	public String delete(Integer id, String category, RedirectAttributes attrs) {
		log.info("delete(id = {}, category = {})", id, category);

		soccerPostsService.delete(id);
		attrs.addFlashAttribute("deletedId", id);

		return "redirect:/view/list?category=" + category;
		// TODO: 삭제한 게시판으로 이동할 수 있게 수정
	}

	@PostMapping("/update")
	public String update(SoccerPostsUpdateDto dto, RedirectAttributes attrs) {
		log.info("update(dto={})", dto);

		Integer postId = soccerPostsService.update(dto);
		attrs.addFlashAttribute("updatedId", postId);

		return "redirect:/view/detail?id=" + postId;
	}
	
//	@GetMapping("/download/{fileId}")
//	public ResponseEntity<Resource> fileDownload(@PathVariable("fileId") Long fileId) throws IOException {
//	    FileDto fileDto = fileService.getFile(fileId);
//	    Path path = Paths.get(fileDto.getFilePath());
//	    InputStreamResource resource = new InputStreamResource(Files.newInputStream(path));
//	    
//	    return ResponseEntity.ok()
//	            .contentType(MediaType.parseMediaType("application/octet-stream"))
//	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getOrigFilename() + "\"")
//	            .body(resource);
//	}
	
}
