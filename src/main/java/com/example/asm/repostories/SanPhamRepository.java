package com.example.asm.repostories;

import com.example.asm.entity.LoaiSP;
import com.example.asm.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SanPhamRepository extends JpaRepository<SanPham,String> {
    @Query("SELECT p FROM SanPham p WHERE p.loaiSP = ?1")
    List<SanPham> getProductByCate(LoaiSP loaiSP);

//    SanPham findByMa(String ma);
}
