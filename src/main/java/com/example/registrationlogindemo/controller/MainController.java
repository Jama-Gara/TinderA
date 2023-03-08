package com.example.registrationlogindemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class MainController {
    @GetMapping("/mainpage")
    public String showMainPage(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username"); // retrieve the username from the session
        model.addAttribute("username", username);
        return "mainpage";
    }
}