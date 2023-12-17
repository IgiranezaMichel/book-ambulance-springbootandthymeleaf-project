package com.bookambulance.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookambulance.Interfaces.DataInterface;
import com.bookambulance.Model.Location;
import com.bookambulance.Repository.LocationRepository;

@Service
public class LocationServices implements DataInterface<Location,Long>{
@Autowired private LocationRepository locationRepository;
@Override
public Location saveOrUpdateData(Location data) {
   return locationRepository.save(data);
}

@Override
public void deleteDataById(Long id) {
   locationRepository.deleteById(id);
}

@Override
public Location findById(Long id) {
   return locationRepository.findById(id).orElse(null);
}

@Override
public List<Location> getAllData() {
  return locationRepository.findAll();
}

public List<Location> getAllProvinces() {
    return locationRepository.findByType("PROVINCE");
}
    

}
