package com.bookambulance.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookambulance.Interfaces.DataInterface;
import com.bookambulance.Model.Booking;
import com.bookambulance.Model.Hospital;
import com.bookambulance.Model.User;
import com.bookambulance.Repository.BookingRepository;
@Service
public class BookingServices implements DataInterface<Booking,Long>{
    @Autowired private BookingRepository bookingRepo;
    @Override
    public Booking saveOrUpdateData(Booking data) {
       return bookingRepo.save(data);
    }

    @Override
    public List<Booking> getAllData() {
       return bookingRepo.findAll();
    }
   @Override
   public void deleteDataById(Long id) {
      bookingRepo.deleteById(id);
   }

   @Override
   public Booking findById(Long id) {
     return bookingRepo.findById(id).orElse(null);
   }
   public List<Booking>ListOfUserBookings(User user){
      return bookingRepo.findByUser(user);
   }

public Hospital findByHospital(Hospital hospital) {
   return bookingRepo.findByHospital(hospital);
}

}
