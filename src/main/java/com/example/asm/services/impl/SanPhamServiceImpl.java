package com.example.asm.services.impl;

import com.example.asm.entity.SanPham;
import com.example.asm.repostories.SanPhamRepository;
import com.example.asm.services.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SanPhamServiceImpl implements SanPhamService {

    @Autowired
    SanPhamRepository sanPhamRepository;

    public SanPham save(SanPham entity) {
        return sanPhamRepository.save(entity);
    }

    public List<SanPham> saveAll(List<SanPham> entities) {
        return sanPhamRepository.saveAll(entities);
    }

    public SanPham findById(String s) {
        return sanPhamRepository.findById(s).get();
    }

    public boolean existsById(String s) {
        return sanPhamRepository.existsById(s);
    }

    public List<SanPham> findAll() {
        return sanPhamRepository.findAll();
    }

    public long count() {
        return sanPhamRepository.count();
    }

    public void deleteById(String s) {
        sanPhamRepository.deleteById(s);
    }
}
