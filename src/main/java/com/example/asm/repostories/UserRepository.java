package com.example.asm.repostories;

import com.example.asm.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,String> {
    Users findUserByUsernameAndPassword(String username, String password);
}
