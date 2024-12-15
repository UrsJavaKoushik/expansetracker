package com.i3t.expanse.repository;

import com.i3t.expanse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String username);
    User save(User user);
    //User update(User user);
    void delete(User user);
    @Modifying
    @Query("update User u set u.profilePicture = ?1 where u.id = ?2")
    void updateProfilePicture(byte[] profilePicture, Long userId);
    
}

