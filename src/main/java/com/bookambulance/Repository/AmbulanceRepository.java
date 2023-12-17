package com.bookambulance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookambulance.Model.Ambulance;

public interface AmbulanceRepository extends JpaRepository<Ambulance,Long> {

}
