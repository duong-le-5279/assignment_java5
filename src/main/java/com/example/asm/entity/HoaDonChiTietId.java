package com.example.asm.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class HoaDonChiTietId {
    @ManyToOne
    @JoinColumn(name = "idHoaDon",referencedColumnName = "id")
    private HoaDon hoaDon;
    @ManyToOne
    @JoinColumn(name = "idSanPham",referencedColumnName = "id")
    private SanPham sanPham;

    public HoaDonChiTietId() {

    }

    public HoaDonChiTietId(HoaDon hoaDon, SanPham sanPham) {
        this.hoaDon = hoaDon;
        this.sanPham = sanPham;
    }
}
