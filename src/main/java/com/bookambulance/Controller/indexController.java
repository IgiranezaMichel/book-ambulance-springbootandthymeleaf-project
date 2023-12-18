package com.bookambulance.Controller;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.bookambulance.Model.User;
import com.bookambulance.Repository.UserRepository;
@Controller
public class indexController {
@Autowired private UserRepository userRepository;
public String getHomePage(){
    return "index";
}


@GetMapping(value = "/login")
public String loginPage(){
    return "login";
}
 @GetMapping(value="/successlogin")
    public String getDefaultLoginPage() {
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       String username=auth.getName();
        User authuser=userRepository.findByEmail(username);
    if(auth!=null && auth.isAuthenticated())
    {
      Collection<? extends GrantedAuthority> authority=auth.getAuthorities();
      if(authority.contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
      { AdminController.setUser(authuser);
        return "redirect:/admin";
      }
      else if(authority.contains(new SimpleGrantedAuthority("ROLE_USER"))){
         UserController.setUser(authuser);
        return "redirect:/user";
      }
    //   else if(authority.contains(new SimpleGrantedAuthority("ROLE_DISTRIBUTOR"))){
    //      DistributorController.user=authuser;
    //     return "redirect:/distributor";
    //   }
    }
        return "/login";
    }
}
