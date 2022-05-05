package com.example.samprojectdb.controller;

import com.example.samprojectdb.entity.DischargeSummary;
import com.example.samprojectdb.entity.FollowUp;
import com.example.samprojectdb.entity.User;
import com.example.samprojectdb.repository.DischargeSummaryRepo;
import com.example.samprojectdb.security.configurer.UserRepository;
import com.example.samprojectdb.security.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/discharge_summary")
@CrossOrigin("*")
public class DischargeSummaryController {
    @Autowired
    JwtRequestFilter jwtRequestFilter;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DischargeSummaryRepo dischargeSummaryRepo;

    //PUT LOGIC OF GETTING FOLLOWUPs BY DSID here.

    @RequestMapping("/getAll")
    @ResponseBody
    public List<DischargeSummary> getAll()
    {
        return dischargeSummaryRepo.findAll();
    }

    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
    }
//    @CrossOrigin(origins = "http://localhost:8100")
    @RequestMapping("/findByAwwId/{aww_id}")
    public List<DischargeSummary> getAllDischargeSummaries(@PathVariable("aww_id") int awwId)
    {
        UserDetails userDetails = jwtRequestFilter.getUserDetails();
        String username = userDetails.getUsername();
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(()->new UsernameNotFoundException("Not found: " + username));
        user.ifPresent(curUser -> System.out.println(curUser.getAww().getAwwId()));

        return dischargeSummaryRepo.findDischargeSummaryByAww_AwwId(awwId);
    }
}
