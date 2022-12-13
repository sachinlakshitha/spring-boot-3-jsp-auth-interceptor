package com.sachinlakshitha.springboot3jspauthinterceptor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StarterController {
    @GetMapping("/starter")
    public String starter() {
        return "starter";
    }
}
