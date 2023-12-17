package com.bookambulance.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
