package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.Role;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.service.UserService;
import com.example.registrationlogindemo.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller

public class AuthController {
    private UserService userService;
    private UserServiceImpl userServiceImpl;

//    @GetMapping("index")
//    public String home(){
//        return "login";
//    }

//    @GetMapping("login")
//    public String loginForm() {
//        // System.out.println(userService.findByEmail("allahverdihajiyev@gmail.com").isPresent());
//        return "login";
//    }
    @RequestMapping("mainpage")
    public String mainpage() {
        return "mainpage";
    }

//    @RequestMapping("/user")
//    public ModelAndView getUser() {
//        User user = new User(7L,"Jhon", "jhon84@gmail.com","123",
//                (List<Role>) new ArrayList<Role>());
//        ModelAndView modelAndView = new ModelAndView("user");
//        modelAndView.addObject("user", user);
//        return modelAndView;
//    }

    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        if (existing!=null) {
            result.rejectValue("email", null,
                    "There is already an account registered with that email");

        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userServiceImpl.saveUser(user);
        return "redirect:/login";
    }
 }


