package com.bookambulance.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookambulance.Model.Ambulance;
import com.bookambulance.Model.Booking;
import com.bookambulance.Model.User;
import com.bookambulance.Services.AmbulanceServices;
import com.bookambulance.Services.BookingServices;
import com.bookambulance.Services.UserServices;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DriverController {
@Autowired private BookingServices bookingServices;
@Autowired private UserServices userServices;
@Autowired private AmbulanceServices ambulanceServices;
private String message="";
@GetMapping(value = "/driver")
public String getAllBoolingList(Model model){
    User user=userServices.findById(Long.parseLong("2"));
    List<Booking> bookingList=user.getAmbulance().getHospital().getBookingList();
    List<Booking> availableBookingList=bookingList.stream().filter(usr->(!(usr.isCanceled())&&!(usr.isDone()))).collect(Collectors
    .toList());
    model.addAttribute("user", user);
    model.addAttribute("bookingList", availableBookingList);
    return "driver/index";
}
@GetMapping(value = "/driver/booking/{id}")
public String approveIsDone(Model model,@PathVariable long id){
    Booking booking=bookingServices.findById(id);
    booking.setDone(true);
    bookingServices.saveOrUpdateData(booking);
    return "redirect:/driver";
}
@GetMapping(value = "/admin/driver/add/{id}")
public String getDriverDutyPage(Model model,@PathVariable long id){
    Ambulance ambulance=ambulanceServices.findById(id);
    if(ambulance==null)
    return "redirect:/admin";
    model.addAttribute("message", message);
    model.addAttribute("ambulance", ambulance);
    message="";
    return "admin/ambulance/addDriver";
}
@PostMapping(value = "/admin/driver/{id}")
public String getDriverDutyPage(@RequestParam(name = "email") String email,@PathVariable long id)
{
    User user=userServices.findByEmail(email);
    if(user!=null){
        log.info(user.getName()+" changes to driver role");
        Ambulance ambulance=ambulanceServices.findById(id);
        user.setAmbulance(ambulance);
        user.setRole("ROLE_DRIVER");
        User user2=userServices.saveOrUpdateData(user);
        message=user2.getName()+" Role has changed to Driver";
    }
    else {
        log.info("user not found");
        message="User not found";}
    return "redirect:/admin/driver/add/"+id;
}
}
