package com.example.samprojectdb.controller;

import com.example.samprojectdb.entity.*;
import com.example.samprojectdb.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/child")
public class ChildController {
    @Autowired
    private ChildRepo childRepo;

    @Autowired
    private GrowthStatusRulesRepo growthStatusRulesRepo;


    @RequestMapping("/findAll")
    public List<Child> findAll() {
        return childRepo.findAll();
    }

    public Optional<GrowthStatusRules> getGrowthStatus(double ht) {
        return growthStatusRulesRepo.findById(ht);
    }

    @RequestMapping("/findByName/{name}")
    public Child findChildByName(@PathVariable("name") String name)
    {
        return childRepo.findChildByName(name);
    }

    @Autowired
    private FollowUpRepo followUpRepo;

    @Autowired
    private DischargeSummaryRepo dischargeSummaryRepo;

    @GetMapping("/children")
    @ResponseBody
    public List<Child> getAllChildren(){
        return childRepo.findAll();
    }

    @PostMapping("/children")
    public Child saveChildDetails(@RequestBody Child child) {
        return childRepo.save(child);
    }

    @PostMapping("/createfollowup")
    public FollowUp saveFollowUp(@RequestBody FollowUp followUp) {
        return followUpRepo.save(followUp);
    }

    @GetMapping("/children/{id}")
    public Child getSingleChild(@PathVariable int id){
        return childRepo.findById(id).get();
    }


    @PutMapping("/children")
    //@RequestMapping( method = RequestMethod.PUT, headers = "Accept=application/json")
    public Child updateChildDetails(@RequestBody Child child){
        return childRepo.save(child);
    }

    @DeleteMapping("/children/{id}")
    public ResponseEntity<HttpStatus> deleteChildById(@PathVariable int id){
        childRepo.deleteById(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/discharge")
    public DischargeSummary dischargeChild(@RequestBody DischargeSummary dischargeSummary){
        return dischargeSummaryRepo.save(dischargeSummary);
    }

    @GetMapping("/dischargedList")
    @ResponseBody
    public List<DischargeSummary> getAllDischarged(){
        return dischargeSummaryRepo.findAll();
    }


}
