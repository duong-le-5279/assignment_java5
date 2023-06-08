package com.example.asm.repostories;

import com.example.asm.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, GioHangChiTietId> {
    List<GioHangChiTiet> findByIdGioHang(GioHang gioHang);

    GioHangChiTiet findByIdSanPham(SanPham sanPham);

    GioHangChiTiet findByIdSanPhamAndIdGioHang(SanPham sanPham, GioHang gioHang);
}
