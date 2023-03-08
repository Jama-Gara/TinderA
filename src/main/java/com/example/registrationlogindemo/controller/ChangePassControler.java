package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.ChangePassRequest;
import com.example.registrationlogindemo.dto.SignInRequest;
import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.Gender;
import com.example.registrationlogindemo.service.UserService;
import com.example.registrationlogindemo.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class ChangePassControler {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private UserService userService;

    @PostMapping("/my-profile/savePass")
    public RedirectView submitMyPass(ChangePassRequest request, HttpSession session,
                                     @ModelAttribute("user") UserDto user
    ) {

        String email = (String) session.getAttribute("sesEmail");
        String password = request.getOldPassword();
        String newPassword = request.getNewPassword();
        String confirmPassword = request.getConfirmPassword();
        log.info("girildi post");
        boolean isAuthenticated =
                passwordEncoder.matches(request.getOldPassword(),userService.findByEmail(email).getPassword()
                )  && newPassword.equals(confirmPassword);
        log.info(userService.findByEmail(email).getEmail());
        log.info(password);
        log.info(userService.findByEmail(email).getPassword());
        if (!(password.isEmpty() && newPassword.isEmpty() && confirmPassword.isEmpty()))
        {
            if (isAuthenticated) {
                user.setPassword(passwordEncoder.encode(newPassword));
                log.info(newPassword);
                log.info(password);
                log.info(passwordEncoder.encode(newPassword));
                log.info("pass deyisimi");

                userServiceImpl.changePassword(user,email);
                return new RedirectView("/my-profile/save");
            } else {
                log.info("yanlış giriş yapıldığını bildiren bir hata mesajı gösterin");
                return new RedirectView("my-profile");
                //           "/login?error=true"
            }
        }
        else{

            return new RedirectView("/my-profile");
        }
    }

}
