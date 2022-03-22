package com.example.samprojectdb;

import com.example.samprojectdb.repository.*;
import com.example.samprojectdb.security.configurer.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {AdmissionRepo.class, AwcRepo.class, AwwRepo.class, CareSpecialistRepo.class, ChildRepo.class, DischargeSummaryRepo.class, FollowUpRepo.class, NrcRepo.class, UserRepository.class})
//@EnableJpaRepositories(basePackageClasses = AwcRepo.class)
public class SaMprojectDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaMprojectDbApplication.class, args);
    }

}
