package com.example.asm.repostories;

import com.example.asm.entity.GioHang;
import com.example.asm.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GioHangRepository extends JpaRepository<GioHang, String> {
    GioHang findByUsers(Users users);
}
