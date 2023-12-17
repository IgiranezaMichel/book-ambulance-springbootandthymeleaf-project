package com.bookambulance.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookambulance.Interfaces.DataInterface;
import com.bookambulance.Model.Ambulance;
import com.bookambulance.Repository.AmbulanceRepository;
@Service
public class AmbulanceServices implements DataInterface<Ambulance,Long>{
    @Autowired private AmbulanceRepository ambulanceRepository;
    @Override
    public Ambulance saveOrUpdateData(Ambulance data) {
       return ambulanceRepository.save(data);
    }

    @Override
    public void deleteDataById(Long id) {
       ambulanceRepository.deleteById(id);
    }

    @Override
    public Ambulance findById(Long id) {
         return ambulanceRepository.findById(id).orElse(null);
    }

    @Override
    public List<Ambulance> getAllData() {
      return ambulanceRepository.findAll();
    }

}
