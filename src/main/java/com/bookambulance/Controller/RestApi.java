package com.bookambulance.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookambulance.Model.Location;
import com.bookambulance.Services.LocationServices;
@RestController @RequestMapping(value="/api") public class RestApi {
@Autowired private LocationServices locationServices;
@PostMapping(value = "/location/{id}")
public List<Location> findLocationById(@PathVariable long id){
    return locationServices.findById(id).getLocationList();
}
}
