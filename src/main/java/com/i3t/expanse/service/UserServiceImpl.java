package com.i3t.expanse.service;

import com.i3t.expanse.model.User;
import com.i3t.expanse.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements  UserService {
private UserRepository userRepository;

//create user and store it in mysql database and return user object
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public User save(User user) throws IOException {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
       /* System.out.println("******* "+Files.readAllBytes(Paths.get("src/main/resources/images/image05.jpg")));
        user.setProfilePicture(Files.readAllBytes(Paths.get("src/main/resources/images/image05.jpg")));*/
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
    }
    
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @Override
    public User updateUser(User user) {
        return null;
    }
    
    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
    
    @Override
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public void updateProfilePicture(MultipartFile file, Long userId) throws IOException {
        if(file.getSize() > 4*1024*1024) {
            throw new IOException("File size should be less than 4MB");
        }
        User user = getUserById(userId);
        user.setProfilePicture(file.getBytes());
        userRepository.save(user);
        
    }
    
}