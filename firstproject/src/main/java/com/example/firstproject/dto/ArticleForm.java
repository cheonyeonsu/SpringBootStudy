package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
//폼데이터를 받아올 그릇.
public class ArticleForm {
    private Long id; //수정form에 태그로 id추가 했으므로 여기에도 추가.
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
