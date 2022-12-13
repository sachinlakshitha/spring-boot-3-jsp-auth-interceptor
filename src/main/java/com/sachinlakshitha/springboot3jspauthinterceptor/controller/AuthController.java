package com.sachinlakshitha.springboot3jspauthinterceptor.controller;

import com.sachinlakshitha.springboot3jspauthinterceptor.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/login")
    public ModelAndView authenticate(HttpSession httpSession, @RequestParam String username,
                                     @RequestParam String password) {

        ModelAndView mav = new ModelAndView();

        UserDto user = null;

        if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("123456")) {
            user = new UserDto();

            user.setId("1");
            user.setUsername(username);
            user.setPassword(password);
        } else {
            mav.addObject("login_error", "Username or password incorrect");
            mav.setViewName("login");
        }

        if (user != null) {
            httpSession.setAttribute("user", user);
            mav.setViewName("redirect:/starter");
        } else {
            mav.addObject("login_error", "Username or password incorrect");
            mav.setViewName("login");
        }

        return mav;
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/login";
    }
}
