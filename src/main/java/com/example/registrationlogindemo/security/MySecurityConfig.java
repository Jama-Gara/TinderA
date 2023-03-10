package com.example.registrationlogindemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
            .withUser("user").password("{noop}password").roles("USER")
            .and()
            .withUser("admin").password("{noop}password").roles("USER", "ADMIN");
  }
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .antMatchers("/**").permitAll()
            .antMatchers("/users").hasRole("ADMIN")
//            .antMatchers("/login").permitAll()
//            .antMatchers("/register").permitAll()
//            .antMatchers("/my-profile").permitAll()
//            .antMatchers("/user").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/authenticate")
            .permitAll()
            .and()
            .formLogin()
            .loginPage("/login")
            .successForwardUrl("/login")
            .failureHandler(authenticationFailureHandler())
            .permitAll()
            .and()

            .logout()
            .logoutUrl("/logout") // Logout i??leminin yap??laca???? URL
            .invalidateHttpSession(true) // HttpSession'??n otomatik olarak ge??ersizle??tirilmesi
            .deleteCookies("JSESSIONID") // Oturum ??erezlerinin silinmesi
            .logoutSuccessUrl("/login") // Ba??ar??l?? logout sonras?? y??nlendirilecek URL
            .and()

            .csrf().disable();
  }

  @Bean
  public AuthenticationFailureHandler authenticationFailureHandler() {
    return new CustomAuthenticationFailureHandler();
  }

  public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
      String errorMessage = "Kullan??c?? ad?? veya ??ifre yanl????.";
      request.getSession().setAttribute("errorMessage", errorMessage);
      response.sendRedirect("/login");
    }
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
