package com.bookambulance.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookambulance.Model.Location;
import com.bookambulance.Services.LocationServices;

import lombok.extern.slf4j.Slf4j;
@Controller
@Slf4j
public class LocationController {
    @Autowired private LocationServices locationServices;
   @GetMapping(value = "/location") public List<Location> getAllProvince(){
        return locationServices.getAllProvinces();
    }
    @PostMapping(value="/location") public Location addLocation(@ModelAttribute() Location locationQl){
        Location location=new Location(); 
        try {
            location=locationServices.findById(locationQl.getId());
            if(!locationQl.equals(""))
            {
                return location;
            }
         } catch (Exception e) {
            log.error(e.getMessage());
         }
        return locationServices.saveOrUpdateData(new Location(locationQl.getName(), locationQl.getType(), location));
    }
    @PostMapping(value="/location/:id") public String deleteLocation(@PathVariable long id)
    {
        try {
            if(!(id==0))
            {
                Location location=locationServices.findById(id);
                if(!(location==null)){
                    locationServices.deleteDataById(id);
                    log.info(location.getName()+" has been removed successfully");
                    return location.getName() +" Deleted successfully";
                }
                else return "Location not found";
            }
            else return "specify Location please";
        } catch (Exception e) {
            log.info("Error happen=>"+e.getMessage());
           return "Location doesn't exist";
        }
    }
    @GetMapping(value = "/location/:id") public Location findLocationById(@PathVariable long id)
    {   try {
        return locationServices.findById(id);
    } catch (Exception e) {
        return null;
    }
    }
    
 
        
}
