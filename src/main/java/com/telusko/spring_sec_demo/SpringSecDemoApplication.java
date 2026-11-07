package com.telusko.spring_sec_demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.telusko.spring_sec_demo.entity.LoginEntity;
import com.telusko.spring_sec_demo.repo.UserRepository;

@SpringBootApplication
public class SpringSecDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecDemoApplication.class, args);
	
}

	@Bean 
    CommandLineRunner seedUsers(UserRepository repo, PasswordEncoder encoder) {
    return args -> {
        if (repo.count() == 0) {
            repo.save(new LoginEntity("alice", encoder.encode("a123"), "USER"));
            repo.save(new LoginEntity("bob", encoder.encode("b123"), "ADMIN"));
        }
    };
	}

}
