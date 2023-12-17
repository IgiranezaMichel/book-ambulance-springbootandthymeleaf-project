package com.bookambulance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookambulance.Model.User;

public interface UserRepository extends JpaRepository<User,Long>{

}
