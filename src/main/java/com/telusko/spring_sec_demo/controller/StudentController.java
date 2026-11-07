package com.telusko.spring_sec_demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.spring_sec_demo.entity.Student;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;


@RestController 
public class StudentController {

    List<Student>students = new ArrayList<>(List.of(
        new Student(1, "Sai", "Java"),
        new Student(2, "Navin", "Kotlin")
    )); 


    
    @GetMapping("/csrf-token")
    public CsrfToken getToken(HttpServletRequest request)
    {
        CsrfToken val = (CsrfToken)request.getAttribute("_csrf");
        return val;
    }

    @GetMapping("/students")
    public List<Student> getStudents()
    {

        System.out.println("Get - "+students);
        return students;
    }

    @PostMapping("/students")
    public String setStudents(@RequestBody Student student)
    {

        System.out.println("Post - "+student);

        try {
             students.add(student);
        } catch (Exception e) {
            return "Failed";
        }
        return "Success";
    }
    

}
