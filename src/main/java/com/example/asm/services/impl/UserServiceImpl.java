package com.example.asm.services.impl;

import com.example.asm.entity.Users;
import com.example.asm.repostories.UserRepository;
import com.example.asm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Users save(Users entity) {
        return userRepository.save(entity);
    }

    @Override
    public List<Users> saveAll(List<Users> entities) {
        return userRepository.saveAll(entities);
    }

    @Override
    public Users findById(String id){
        return userRepository.findById(id).get();
    }

    @Override
    public boolean existsById(String s) {
        return userRepository.existsById(s);
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public void deleteById(String s) {
        userRepository.deleteById(s);
    }
}
