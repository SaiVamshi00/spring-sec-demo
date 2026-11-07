package com.telusko.spring_sec_demo.controller;

import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HelloController {

    @GetMapping("/hello")
    public String greet(HttpServletRequest o)
    {
        return "Hello World! " + o.getSession().getId();
    }

    @GetMapping("/about")
    public String about(HttpServletRequest o)
    {
        return "Telusko " + o.getRequestedSessionId();
    }

    @GetMapping("/public")
    public String publicPage()
    {
        return "Anyone can see this!";
    }

    @GetMapping("/user/dashboard")
    public String userDashboard() {
         return "User Dashboard"; 
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "Admin Dashboard";
    }

    @GetMapping("/me")
    public Object me(Authentication auth) {
        return auth.getAuthorities();
    }

        // Way 1: from anywhere (controllers, services...)
    @GetMapping("/me/context")
    public Map<String, Object> fromContext() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return Map.of(
            "name", auth.getName(),
            "authorities", auth.getAuthorities(),
            "authClass", auth.getClass().getSimpleName(),
            "principalClass", auth.getPrincipal().getClass().getName(),
            "credentials", String.valueOf(auth.getCredentials())
        );
    }

    // Way 2: Spring injects the Authentication (your /me from Step 4)

    // Way 3: Spring injects the principal directly
    @GetMapping("/me/principal")
    public String fromPrincipal(@AuthenticationPrincipal UserDetails user) {
        return "Hello " + user.getUsername();
    }

    @GetMapping("/reports")
    @PreAuthorize("hasRole('ADMIN')")
    public String reports() {
        return "Secret Reports";
    }
    
    
    

}
