package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

@GetMapping("/hi")
    public String niceToMeetYou(Model model){
    //모델을 통해서 변수를 등록. 달걀 이름을 username에 연결.
    model.addAttribute("username","EGG");
        return "greetings"; //templates/greetings.mustache > 브라우저로 전송.
    }
}
