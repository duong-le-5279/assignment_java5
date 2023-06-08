package com.example.asm.services;

import com.example.asm.entity.LoaiSP;

import java.util.List;

public interface LoaiSPService {
    List<LoaiSP> saveAll(List<LoaiSP> entities);

    List<LoaiSP> findAll();

    LoaiSP save(LoaiSP entity);

//    List<LoaiSP> findAllById(List<String> strings);

    LoaiSP findById(String id);

    boolean existsById(String s);

    void deleteById(String s);
}
