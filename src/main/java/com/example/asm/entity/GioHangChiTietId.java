package com.example.asm.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class GioHangChiTietId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "idGioHang",referencedColumnName = "id")
    private GioHang gioHang;
    @ManyToOne
    @JoinColumn(name = "idSanPham",referencedColumnName = "id")
    private SanPham sanPham;

    public GioHangChiTietId() {

    }

    public GioHangChiTietId(GioHang gioHang, SanPham sanPham) {
        this.gioHang = gioHang;
        this.sanPham = sanPham;
    }
}
