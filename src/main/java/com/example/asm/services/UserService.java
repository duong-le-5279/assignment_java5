package com.example.asm.services;

import com.example.asm.entity.Users;

import java.util.List;

public interface UserService {
    Users save(Users entity);

    List<Users> saveAll(List<Users> entities);

    Users findById(String id);

    boolean existsById(String s);

    List<Users> findAll();

    long count();

    void deleteById(String s);
}
