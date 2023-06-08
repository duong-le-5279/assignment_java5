package com.example.asm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "SanPham")
public class SanPham implements Serializable {
    @Id
    @GenericGenerator(name = "generator",strategy = "guid")
    @GeneratedValue(generator = "generator")
    @Column(columnDefinition = "uniqueidentifier default NEWID()")
    private String id;
    @NotBlank
    @JoinColumn(unique = true)
    private String ma;
    @NotBlank
    private String ten;
    @NotNull
    @Min(0)
    private long giaNhap;
    @NotNull
//    @DecimalMin(value = , inclusive = true)
    private long giaBan;
    @NotNull
    private long soLuong;
    private String moTa;
    @ManyToOne
    @JoinColumn(name = "idLoai",referencedColumnName = "id")
    private LoaiSP loaiSP;
    private String image;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayNhap;
}
