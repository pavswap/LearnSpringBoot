package com.Thymleaf.ServerFrontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", "Paavan Here!");
        return "index";
    }
}
