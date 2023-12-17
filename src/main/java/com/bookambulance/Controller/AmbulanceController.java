package com.bookambulance.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.bookambulance.Model.Ambulance;
import com.bookambulance.Model.Hospital;
import com.bookambulance.Services.AmbulanceServices;
import com.bookambulance.Services.HospitalServices;

@Controller
public class AmbulanceController {
@Autowired private AmbulanceServices ambulanceServices;
@Autowired HospitalServices hospitalServices;
private String message="";
@GetMapping(value = "/admin/ambulance")
public String getAmbulance(Model model){
    model.addAttribute("ambulance", new Hospital());
    model.addAttribute("hospitalList", hospitalServices.getAllData());
    model.addAttribute("ambulanceList", ambulanceServices.getAllData());
    model.addAttribute("message", message);
    message="";
    return "admin/ambulance/add";
}
@PostMapping(value = "/admin/ambulance")
public String saveAmbulance(@ModelAttribute Ambulance ambulance){
   Ambulance ambulance2= ambulanceServices.saveOrUpdateData(ambulance);
   message="Car having plateNumber "+ambulance2.getPlateNumber()+" has saved successfully";
    return "redirect:/admin/ambulance";
}
}
