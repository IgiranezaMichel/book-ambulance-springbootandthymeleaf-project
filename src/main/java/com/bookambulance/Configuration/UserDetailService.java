package com.bookambulance.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bookambulance.Model.User;
import com.bookambulance.Repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailService implements UserDetailsService{
@Autowired private UserRepository userrepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
        User  user =userrepo.findByEmail(username);
        log.info(username);
        if(user == null){
        log.error(username+" User not found");
        throw new UsernameNotFoundException("Unimplemented method  loadUserByUsername");};
        log.info(user.getEmail());
    return new UserDetailPrinciple(user);
    }
}
