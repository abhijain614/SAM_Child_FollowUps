package com.example.samprojectdb.controller;

import com.example.samprojectdb.entity.Admission;
import com.example.samprojectdb.entity.User;
import com.example.samprojectdb.repository.AdmissionRepo;
import com.example.samprojectdb.security.configurer.UserRepository;
import com.example.samprojectdb.security.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admission")
public class AdmissionController {
    @Autowired
    private AdmissionRepo admissionRepo;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/getAll")
    @ResponseBody
    public List<Admission> getAll()
    {
        UserDetails userDetails = jwtRequestFilter.getUserDetails();
        String username = userDetails.getUsername();
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty())
        {
            return new ArrayList<>();
        }
        List<Admission> list = admissionRepo.findAll();
//        for(Admission a:list)
//        {
//            System.out.println(a);
//        }
        return  list;
    }
}
