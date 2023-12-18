package com.bookambulance.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bookambulance.Model.Booking;
import com.bookambulance.Model.User;
import com.bookambulance.Services.BookingServices;
import com.bookambulance.Services.HospitalServices;
import com.bookambulance.Services.UserServices;
@Controller
@SessionAttributes({"user"})
public class UserController {
private String message="";
@Autowired private UserServices userServices;
@Autowired private HospitalServices hospitalServices;
@Autowired private BookingServices bookingServices;
private static  User user1;
public static void setUser(User user){
user1=user;
}
@GetMapping(value = "/signup")
public String signupPage(Model model){
  model.addAttribute("message", message);
  model.addAttribute("user", new User());
  message="";
    return "signup";
}
@RequestMapping(value = "/login")
public String loginPage(){
    return "login";
}
@GetMapping(value = "/user")
public String userHomePage(Model model){
  model.addAttribute("message", message);
  List<Booking>AllbookingList=bookingServices.ListOfUserBookings(user1);
  List<Booking>bookingList=AllbookingList.stream().filter(user->!user.isCanceled()).collect(Collectors.toList());
  model.addAttribute("userBooking", bookingList);
  model.addAttribute("userBookingHistory", AllbookingList);
  model.addAttribute("booking", new Booking());
  model.addAttribute("user", user1);
  message="";
    return "user/index";
}
@PostMapping(value = "/signup") public String addUser(@ModelAttribute User userinput)
{   try {
  userinput.setRole("ROLE_USER");
  userinput.setPassword(BCrypt.hashpw(userinput.getPassword(), BCrypt.gensalt()));
 User user= userServices.saveOrUpdateData(userinput);
 message=user.getName()+" Saved successfull";
} catch (Exception e) {
 message="Please verify your email";
}
return "redirect:/signup";
}

  @GetMapping(value = "/user/{id}") public String findUserById(@PathVariable long id,Model model)
    {   try {
          User user= userServices.findById(id);
          model.addAttribute("user", user);
          model.addAttribute("booking", new Booking());
          model.addAttribute("hospitalList", hospitalServices.getAllData());
          model.addAttribute("message", message);
          message="";
         return "/user/book";
    } catch (Exception e) {
      return "redirect:/user" ; 
    }
    
    }
   
@PostMapping(value = "user/:id") public String deleteUser(@PathVariable long id){
  try {
    User user=userServices.findById(id);
    if(user!=null)
       {
        userServices.deleteDataById(user.getId());
        return user.getName()+" Removed Successfully";
      }
      else{
        return "User not found";
      }
  } catch (Exception e) {
    return "Server error";
  }
}
@GetMapping(value = "/admin/user/{id}/{action}")
public String getUserDetail(@PathVariable long id,@PathVariable String action,Model model){
    User user1=userServices.findById(id);
  if(!(user1==null))
  { model.addAttribute("action", action);
    model.addAttribute("user", user1);
    model.addAttribute("user1", new User());
    return "admin/user/crud";
  }
  else{
    message="User not found";
    return "redirect:/admin";
  }
}
@PostMapping(value = "/admin/user/delete/{id}") 
public String StringeleteOrUpdate(@PathVariable long id){
User user1=userServices.findById(id);
if(!(user1==null)){
    userServices.deleteDataById(user1.getId());
    message=user1.getName()+" has been deleted successfully";
}
else message="User not found";
return "redirect:/admin";
}
}
