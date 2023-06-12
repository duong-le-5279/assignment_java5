package com.example.asm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "HoaDon")
public class HoaDon implements Serializable {
    @Id
    @GenericGenerator(name = "generator",strategy = "guid")
    @GeneratedValue(generator = "generator")
    @Column(columnDefinition = "uniqueidentifier default NEWID()")
    private String id;
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            columnDefinition = "uniqueidentifier"
    )
    private Users users;
    private String ma;
    private String trangThai;
    private Date ngayTao;
    private Date ngayThanhToan;
    private Long tongGia;
    public HoaDon(Users users, String ma, String trangThai, Date ngayTao, Date ngayThanhToan, Long tongGia) {
        this.users = users;
        this.ma = ma;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.tongGia = tongGia;
    }
}
