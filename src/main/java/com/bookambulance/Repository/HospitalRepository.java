package com.bookambulance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bookambulance.Model.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital,Long>{

}
