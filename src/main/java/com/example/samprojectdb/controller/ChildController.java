package com.example.samprojectdb.controller;

import com.example.samprojectdb.entity.Admission;
import com.example.samprojectdb.entity.Child;
import com.example.samprojectdb.entity.GrowthStatusRules;
import com.example.samprojectdb.entity.User;
import com.example.samprojectdb.repository.ChildRepo;
import com.example.samprojectdb.repository.GrowthStatusRulesRepo;
import com.example.samprojectdb.security.configurer.UserRepository;
import com.example.samprojectdb.security.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.samprojectdb.entity.GrowthStatusRules;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/child")
public class ChildController {
    @Autowired
    private ChildRepo childRepo;

    @Autowired
    private GrowthStatusRulesRepo growthStatusRulesRepo;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Autowired
    UserRepository userRepository;

    //    @RequestMapping("/demo")
//    public void display()
//    {
//        System.out.println(childRepo.findAll().toString());
//    }
    @RequestMapping("/findAll")
    public List<Child> findAll()
    {
        UserDetails userDetails = jwtRequestFilter.getUserDetails();
        String username = userDetails.getUsername();
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty())
        {
            return new ArrayList<>();
        }
        return childRepo.findAll();
    }

    public Optional<GrowthStatusRules> getGrowthStatus(double ht)
    {
        UserDetails userDetails = jwtRequestFilter.getUserDetails();
        String username = userDetails.getUsername();
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty())
        {
            return Optional.ofNullable(new GrowthStatusRules());
        }
        return growthStatusRulesRepo.findById(ht);
    }
//    //Return current growth status as per as latest followup
//    @GetMapping("/getCurrentGrowthStatus/{samId}")
//    public String findGS(@PathVariable("samId") int samId) {
//        Child tempChild = childRepo.findBySamId(samId);
//        if(tempChild==null)
//            return "NA";
//        //System.out.println(childRepo.findHeightBySamId(samId));
//        double ht = tempChild.getHeight();
//        Optional<GrowthStatusRules> growthStatusRules = getGrowthStatus(ht);
//        if (growthStatusRules.isPresent()) {
//            GrowthStatusRules growthStatusRules1 = growthStatusRules.get();
//            double threshold = 0;
//            switch (tempChild.getGender()) {
//                case 'M':
//                    threshold = growthStatusRules1.getBoys3d();
//                    break;
//                case 'F':
//                    threshold = growthStatusRules1.getGirls3d();
//                    break;
//                default:
//                    System.out.println("Some error here in FollowUpController gender switch case!!");
//                    System.exit(0);
//                    break;
//            }
//            if (tempChild.getWeight() < threshold) {
//                System.out.println("SAM");
//                return "SAM";
//            } else {
//                System.out.println("Normal");
//                return "Normal";
//            }
//        }
//        return "NA";
//    }

    @RequestMapping("/findByName/{name}")
    public Child findChildByName(@PathVariable("name") String name)
    {
        UserDetails userDetails = jwtRequestFilter.getUserDetails();
        String username = userDetails.getUsername();
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty())
        {
            return new Child();
        }
        return childRepo.findChildByName(name);
    }
}
