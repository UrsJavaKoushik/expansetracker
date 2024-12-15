package com.i3t.expanse.service;

import com.i3t.expanse.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User save(User user) throws IOException;

    User getUserById(Long userId);
    List<User> getAllUsers();
    User updateUser(User user);
    void deleteUser(Long userId);
    User findByUserName(String email);

    void updateProfilePicture(MultipartFile file, Long userId) throws IOException;
}
