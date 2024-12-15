package com.i3t.expanse.controller;

import com.i3t.expanse.model.User;
import com.i3t.expanse.security.CustomUserDetailsService;
import com.i3t.expanse.security.JwtHelper;
import com.i3t.expanse.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    @Autowired
    UserService userService;
   
    //create user
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try{
            return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
