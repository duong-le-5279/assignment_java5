package com.example.asm.repostories;

import com.example.asm.entity.HoaDon;
import com.example.asm.entity.HoaDonChiTiet;
import com.example.asm.entity.HoaDonChiTietId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, HoaDonChiTietId> {
    @Query("SELECT hd from HoaDonChiTiet hd where hd.hoaDonChiTietId.hoaDon = ?1")
    List<HoaDonChiTiet> findByIdHoaDon(HoaDon hd);
}
