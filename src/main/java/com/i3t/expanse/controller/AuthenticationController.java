package com.i3t.expanse.controller;

import com.i3t.expanse.model.JwtRequest;
import com.i3t.expanse.model.JwtResponse;
import com.i3t.expanse.model.User;
import com.i3t.expanse.security.CustomUserDetailsService;
import com.i3t.expanse.security.JwtHelper;
import com.i3t.expanse.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    private AuthenticationManager manager;
    
    
    @Autowired
    private JwtHelper helper;
    
    private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    
    
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        this.doAuthenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);
        JwtResponse response = JwtResponse.builder().jwtToken(token).username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    private void doAuthenticate(String userName, String password) {
        System.out.println(userName+"\t"+password);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }
    
    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.getContext().setAuthentication(null);
        return new ResponseEntity<>("You have been logged out successfully", HttpStatus.OK);
    }
}