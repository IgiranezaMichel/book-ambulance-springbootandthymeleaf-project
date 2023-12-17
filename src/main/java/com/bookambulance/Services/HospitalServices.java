package com.bookambulance.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookambulance.Interfaces.DataInterface;
import com.bookambulance.Model.Hospital;
import com.bookambulance.Repository.HospitalRepository;
@Service
public class HospitalServices implements DataInterface<Hospital,Long>{
    @Autowired private HospitalRepository hospitalRepository;
    @Override
    public Hospital saveOrUpdateData(Hospital data) {
        return hospitalRepository.save(data);
    }
    @Override
    public List<Hospital> getAllData() {
       return hospitalRepository.findAll();
    }
    @Override
    public void deleteDataById(Long id) {
      hospitalRepository.deleteById(id);
    }
    @Override
    public Hospital findById(Long id) {
       return hospitalRepository.findById(id).orElse(null);
    }

}
