package com.example.samprojectdb.repository;

import com.example.samprojectdb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User,Integer> {

    @Query(value="SELECT * FROM user WHERE username=?1",nativeQuery = true)
    User getUserByUsername(String username);

    @Query(value="SELECT * FROM user WHERE user_id=?1",nativeQuery = true)
    User getUserByID(Long id);
}
