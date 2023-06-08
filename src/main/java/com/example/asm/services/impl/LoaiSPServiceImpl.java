package com.example.asm.services.impl;

import com.example.asm.entity.LoaiSP;
import com.example.asm.repostories.LoaiSPRepository;
import com.example.asm.services.LoaiSPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiSPServiceImpl implements LoaiSPService{
    @Autowired
    LoaiSPRepository loaiSPRepository;

    @Override
    public List<LoaiSP> saveAll(List<LoaiSP> entities) {
        return loaiSPRepository.saveAll(entities);
    }

    @Override
    public List<LoaiSP> findAll() {
        return loaiSPRepository.findAll();
    }

    @Override
    public LoaiSP save(LoaiSP entity) {
        return loaiSPRepository.save(entity);
    }

    @Override
//    public LoaiSP findById(String id){
//        return loaiSPRepository.getReferenceById(id);
//    }
    public LoaiSP findById(String id){
        return loaiSPRepository.findById(id).get();
    }

    @Override
    public boolean existsById(String s) {
        return loaiSPRepository.existsById(s);
    }

    @Override
    public void deleteById(String s) {
        loaiSPRepository.deleteById(s);
    }
}
