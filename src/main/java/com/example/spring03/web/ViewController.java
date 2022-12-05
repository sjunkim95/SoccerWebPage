package com.example.spring03.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring03.domain.Info;
import com.example.spring03.dto.InfoCreateDto;
import com.example.spring03.service.InfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/view")
public class ViewController {
    
    private final InfoService infoService;
    
    @GetMapping
    public String main(){
        log.info("main()...");

        return "main";
    }
    
    @GetMapping("/login")
    public String loginPage() {
        log.info("login()");
        
        return "/view/login";
        
    }
    
    @GetMapping("/example")
    public String example() {
        log.info("example()");
        
        return "/view/example";
    }
    
    @GetMapping("/info")
    public String info() {
        log.info("info()");
        
        return "/view/info";
    }
    
    @GetMapping("/createBoard")
    public String createBoard() {
        log.info("createBoard()");
        
        return "/view/createBoard";
    }
    
    @PostMapping("/createBoardDto")
    public String createBoard(InfoCreateDto dto, RedirectAttributes attrs) {
        
        log.info("createBoardDto()");
        
        Info entity = infoService.create(dto);
        attrs.addFlashAttribute("createdId", entity.getId());
        
        return "redirect:/";
    }
    @GetMapping({"/detail", "/modify"})
    public void detail(Integer id, Model model) {
         log.info("detail(id={})", id);
         
         Info post = infoService.read(id);
         model.addAttribute("info", post);
    }
    
   
    
    @GetMapping("/list")
        public String read(Model model) {
            log.info("list()");
            
            List<Info> list = infoService.read();
            model.addAttribute("list", list);
            return "/info/list";
    }
    
    
    
}

