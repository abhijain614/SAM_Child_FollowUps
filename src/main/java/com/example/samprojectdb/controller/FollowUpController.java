package com.example.samprojectdb.controller;
//import org.springframework.stereotype.Controller;
import com.example.samprojectdb.entity.FollowUp;
import com.example.samprojectdb.entity.GrowthStatusRules;
import com.example.samprojectdb.repository.FollowUpRepo;
import com.example.samprojectdb.repository.GrowthStatusRulesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/followup")
public class FollowUpController {
    @Autowired
    private FollowUpRepo followUpRepo;

    @Autowired
    private GrowthStatusRulesRepo growthStatusRulesRepo;
    //@RequestMapping("/getFollowUpIdBySamId")

    @GetMapping("/get/{fid}")
    public Optional<FollowUp> getFollowUp(@PathVariable("fid") int fid)
    {
        return followUpRepo.findById(fid);
    }
//    @GetMapping("/get/all")
//    public List<GrowthStatusRules> getALl()
//    {
//
//        return growthStatusRulesRepo.findAll();
//    }

    public Optional<GrowthStatusRules> getGrowthStatus(double ht)
    {
        return growthStatusRulesRepo.findById(ht);
    }

    @CrossOrigin(origins = "*")
    @PutMapping(value = "/update",consumes = {"application/json"})
    public Optional<FollowUp> updateFollowUp(@RequestBody FollowUp followUp)
    {
        Integer fid = followUp.getFollowUpId();
        Optional<FollowUp> followUp1 = followUpRepo.findById(fid);
        if(followUp1.isPresent())
        {
            FollowUp followUp2 = followUp1.get();
            if(followUp2.getAttempted())
            {
                System.out.println("This followUp has already been attempted");
                return null;
            }
            followUp2.setAttemptedDate(followUp.getAttemptedDate());
            followUp2.setAttempted(true);
            followUp2.setHeight(followUp.getHeight());
            followUp2.setWeight(followUp.getWeight());
            followUp2.setMuac(followUp.getMuac());
            followUp2.setSymptoms(followUp.getSymptoms());
            //PUT HERE LOGIC TO UPDATE THE GROWTH STATUS BASED ON ANTHROPOMETRIC DATA
            Optional<GrowthStatusRules> growthStatusRules=getGrowthStatus(followUp.getHeight());
            if(growthStatusRules.isPresent())
            {
                GrowthStatusRules growthStatusRules1 = growthStatusRules.get();
                double threshold=0;
                switch (followUpRepo.findGenderByFollowUpId(followUp.getFollowUpId()))
                {
                    case 'M':
                        threshold=growthStatusRules1.getBoys3d(); break;
                    case 'F':
                        threshold=growthStatusRules1.getGirls3d(); break;
                    default:
                        System.out.println("Some error here in FollowUpController gender switch case!!");
                        System.exit(0);
                        break;
                }
                if(followUp.getWeight()<threshold)
                {
                    System.out.println("SAM");
                    followUp2.setGrowthStatus("SAM");
                }
                else
                {
                    System.out.println("Normal");
                    followUp2.setGrowthStatus("Normal");
                }
            }
            else{
                System.out.println("Some error with finding growth status rules!!!");
            }
            followUpRepo.save(followUp2);

        }
        else {
            System.out.println("Could not find the followUp!!!");
        }

        return followUpRepo.findById(fid);
    }

}
