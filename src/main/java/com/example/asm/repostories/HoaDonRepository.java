package com.example.asm.repostories;

import com.example.asm.entity.HoaDon;
import com.example.asm.entity.HoaDonChiTiet;
import com.example.asm.entity.SanPham;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface HoaDonRepository extends JpaRepository<HoaDon, String> {
    @Modifying
    @Query("SELECT hdct FROM HoaDon h join HoaDonChiTiet hdct on h.id = hdct.hoaDonChiTietId.hoaDon.id " +
            "where h.id = ?1")
    List<HoaDonChiTiet> getProductByIdOrder(String id);

    @Query("SELECT h FROM Users u " +
            "join HoaDon h on u.id = h.users.id " +
            "where u.id = ?1")
    List<HoaDon> getOrderByUserId(String id);
}
