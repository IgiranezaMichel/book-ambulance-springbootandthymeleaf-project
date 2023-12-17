package com.bookambulance.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookambulance.Model.User;

@Controller
public class indexController {
public String getHomePage(){
    return "index";
}


@GetMapping(value = "/login")
public String loginPage(){
    return "login";
}
}
