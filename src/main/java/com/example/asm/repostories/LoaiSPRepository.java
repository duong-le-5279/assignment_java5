package com.example.asm.repostories;

import com.example.asm.entity.LoaiSP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiSPRepository extends JpaRepository<LoaiSP, String> {
    LoaiSP findByMa(String ma);

    @Query("SELECT l FROM LoaiSP l WHERE l.ten = ?1")
    LoaiSP findbyTen(String ten);
}
