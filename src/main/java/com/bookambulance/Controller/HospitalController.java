package com.bookambulance.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookambulance.Model.Hospital;
import com.bookambulance.Services.HospitalServices;
import com.bookambulance.Services.LocationServices;

@Controller
@RequestMapping("/hospital")
public class HospitalController {
@Autowired private HospitalServices hospitalServices;
@Autowired private LocationServices locationServices;
private String message="";
@GetMapping(value = "/add") public  String getHospitalById(Model model)
{   model.addAttribute("hospital", new Hospital());
    model.addAttribute("message", message);
    model.addAttribute("provinceList", locationServices.findByLocationType("PROVINCE"));
    return "admin/hospital/add";
}
@GetMapping(value = "/hospital/:id") public  String getHospitalById(@PathVariable long id)
{
    return "admin/hospital/add";
}
@GetMapping(value = "/hospital/all") public List<Hospital> getAllHospital()
{
    return hospitalServices.getAllData();
}
@PostMapping(value = "/add") public String addHospital(@ModelAttribute()Hospital HospitalInput)
{  Hospital hospital=hospitalServices.saveOrUpdateData(HospitalInput);
    message=hospital.getName()+" Added succefully";
    return "redirect:/hospital/add";
}

@PostMapping(value = "/:id") public String deleteHospital(@PathVariable long id){
   if(!(id==0))
   {
     Hospital hospital=hospitalServices.findById(id);
    if(!(hospital==null)){
        hospitalServices.deleteDataById(hospital.getId());
        return hospital.getName() +" Deleted successfully";
    }
    else{
        return "Hospital not found";
    }
   }
   else{
    return "Please specify Hospital you want to delete";
   }
}

}
