package com.bookambulance.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bookambulance.Model.Hospital;
import com.bookambulance.Model.User;
import com.bookambulance.Services.AmbulanceServices;
import com.bookambulance.Services.HospitalServices;
import com.bookambulance.Services.UserServices;
@Controller
@SessionAttributes("admin")
@RequestMapping(value = "/admin")
public class AdminController {
@Autowired private UserServices userServices;
@Autowired private HospitalServices hospitalServices;
@Autowired private AmbulanceServices ambulanceServices;
private static  User user1;
public static void setUser(User user){
user1=user;
}
private String message="";
@GetMapping()
public String adminPage(Model model){
    model.addAttribute("message", message);
    model.addAttribute("userList", userServices.getAllData());
    model.addAttribute("hospitalList", hospitalServices.getAllData());
    model.addAttribute("ambulanceList", ambulanceServices.getAllData());
    model.addAttribute("admin",user1);
    model.addAttribute("user", new User());
    model.addAttribute("hospital", new Hospital());
    return "admin/index";
}

@PostMapping(value = "/update/{id}/{role}")
public String updateUserDetail(@PathVariable long id,@PathVariable String role,Model model){
    User user1=userServices.findById(id);
  if(!(user1==null)){
   User user= userServices.saveOrUpdateData(user1);
    message=user.getName()+" Role has been added successfully";
}
else message="User not found";
return "redirect:/admin";
 
}

}
