package com.telusko.spring_sec_demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telusko.spring_sec_demo.entity.LoginEntity;
import java.util.List;
import java.util.Optional;

@Repository 
public interface UserRepository extends JpaRepository<LoginEntity,Long>{

        Optional<LoginEntity> findByUserName(String userName);
    
}
