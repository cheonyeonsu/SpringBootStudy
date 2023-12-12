package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j //로깅 기능 사용
@Controller
public class ArticleController {
    @Autowired //스프링부트가 미리 생성해 놓은 리파지터리 객체 주입
    private ArticleRepository articleRepository; // 2)articleRepository객체 선언

    @GetMapping("/articles")
    public String index(Model model){ //model 객체 받아오기
        // 1. 모든 데이터 가져오기
        ArrayList<Article> articleEntityList = articleRepository.findAll();
        // 2. 모델에 데이터 등록하기 : 데이터를 뷰 페이지로 전달할 때는 model 사용.
        model.addAttribute("articleList",articleEntityList); //articleEntityList등록
        // 3. 뷰 페이지 설정하기
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit") //url요청 접수
    public String edit(@PathVariable Long id, Model model){ //id를 매개변수로 받아오기, model객체 받아오기
        //수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null); //db에서 수정할 데이터 가져오기.
        //모델에 데이터 등록하기
        model.addAttribute("article",articleEntity); //articleEntity를 article로 등록
        //뷰 페이지 설정하기
        return "articles/edit";
    }

   @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
       log.info(form.toString()); //로깅 코드 추가.
       //System.out.println(form.toString());

       // 1.DTO를 엔티티로 변환.
       Article article = form.toEntity();
       log.info(article.toString());
       //System.out.println(article.toString());

        // 2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article); // 1)article 엔티티를 저장해 saved 객체에 반환
        log.info(saved.toString());
        //System.out.println(saved.toString());
        return "redirect:/articles/"+saved.getId(); //id에 따라 URL주소 달라짐. id값을 가져오기 위해 saved객체 사용.
    }

    @GetMapping("/articles/{id}") //데이터 조회 요청 접수
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id); //id를 잘 받았는지 확인하는 로그 찍기.
        //1. id를 조회해 데이터 가져오기
       Article articleEntity = articleRepository.findById(id).orElse(null); //데이터 조회 후 값이 있으면 articleEntity변수에 값을 넣음.

        //2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity); //article이라는 이름으로 articleEntity객체 추가.

        //3. 뷰 페이지 반환하기
        return "articles/show";

    }
}
