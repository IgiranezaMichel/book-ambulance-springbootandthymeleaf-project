package com.bookambulance.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bookambulance.Model.Booking;
import com.bookambulance.Model.Hospital;
import com.bookambulance.Model.User;
public interface BookingRepository extends JpaRepository<Booking,Long>{

    List<Booking> findByUser(User user);

    Hospital findByHospital(Hospital hospital);

}
