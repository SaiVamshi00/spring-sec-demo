package com.telusko.spring_sec_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.telusko.spring_sec_demo.entity.LoginEntity;
import com.telusko.spring_sec_demo.repo.UserRepository;

@Service 
public class MyUser implements UserDetailsService{

@Autowired 
private UserRepository userRepository;

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    System.out.println("Loding user from DB");

    LoginEntity dbInfo = userRepository.findByUserName(username).orElseThrow(()->new UsernameNotFoundException("Unable to find "+username));

   return User.
    withUsername(username).
    password(dbInfo.getPassword()).
    roles(dbInfo.getRole()).
    build();

}
}
