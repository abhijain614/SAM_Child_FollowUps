package com.example.samprojectdb.security.controller;

import com.example.samprojectdb.entity.User;
import com.example.samprojectdb.security.beans.AuthenticateResponse;
import com.example.samprojectdb.security.configurer.MyUserDetailsService;
import com.example.samprojectdb.security.configurer.UserRepository;
import com.example.samprojectdb.security.filters.JwtRequestFilter;
import com.example.samprojectdb.security.util.jwtutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class ApiController
{
    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private jwtutil jwtutilToken;

    @Autowired
    private UserRepository userRepository;



    @RequestMapping({"/currentUser"})
    public Optional<User> getuser() throws UsernameNotFoundException
    {
        UserDetails userDetails = jwtRequestFilter.getUserDetails();
        String username = userDetails.getUsername();
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(()->new UsernameNotFoundException("Not found: " + username));
        return user;
    }

    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
    }
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User authenticateRequest) throws Exception
    {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateRequest.getUsername(), authenticateRequest.getPassword()));
        }catch(BadCredentialsException e)
        {
            throw new Exception("Incorrect Username or password", e);
        }
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticateRequest.getUsername());
        final String jwt = jwtutilToken.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticateResponse(jwt));
    }
}
