package com.example.asm.repostories;

import com.example.asm.entity.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, GioHangChiTietId> {
    List<GioHangChiTiet> findByIdGioHang(GioHang gioHang);

    GioHangChiTiet findByIdSanPham(SanPham sanPham);

    GioHangChiTiet findByIdSanPhamAndIdGioHang(SanPham sanPham, GioHang gioHang);

    @Modifying
    @Query("DELETE FROM GioHangChiTiet gh WHERE gh.id.gioHang = ?1")
    int deleteByIdGioHang(GioHang gh);
}
