package com.example.samprojectdb.controller;
//import org.springframework.stereotype.Controller;
import com.example.samprojectdb.entity.FollowUp;
import com.example.samprojectdb.repository.FollowUpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/followup")
public class FollowUpController {
    @Autowired
    private FollowUpRepo followUpRepo;

    //@RequestMapping("/getFollowUpIdBySamId")

    @GetMapping("/get/{fid}")
    public Optional<FollowUp> getFollowUp(@PathVariable("fid") int fid)
    {
        return followUpRepo.findById(fid);
    }

    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
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
            followUp2.setAttemptedDate(followUp.getAttemptedDate());
            followUp2.setAttempted(true);
            followUp2.setHeight(followUp.getHeight());
            followUp2.setWeight(followUp.getWeight());
            followUp2.setMuac(followUp.getMuac());
            followUp2.setSymptoms(followUp.getSymptoms());
            followUp2.setGrowthStatus(followUp.getGrowthStatus());
            followUpRepo.save(followUp2);
            //PUT HERE LOGIC TO UPDATE THE GROWTH STATUS BASED ON ANTHROPOMETRIC DATA
        }
        else {
            System.out.println("Could not find the followUp!!!");
        }
        //CREATE NEXT FOLLOWUP HERE.
        return followUpRepo.findById(fid);
    }

}
