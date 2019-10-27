package com.anapiashko.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping
    public String hello() {
        return "greeting.html";
    }
}
