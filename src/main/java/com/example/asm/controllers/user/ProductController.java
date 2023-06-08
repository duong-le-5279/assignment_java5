package com.example.asm.controllers.user;

import com.example.asm.entity.*;
import com.example.asm.repostories.GioHangChiTietRepository;
import com.example.asm.repostories.GioHangRepository;
import com.example.asm.repostories.LoaiSPRepository;
import com.example.asm.repostories.SanPhamRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    GioHangChiTietRepository gioHangChiTietRepository;

    @Autowired
    GioHangRepository gioHangRepository;

    @Autowired
    LoaiSPRepository loaiSPRepository;

    @GetMapping("/list")
    public String productPage(Model model,
                              @RequestParam(name = "pageSize", defaultValue = "9") Integer pageSize,
                              @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                              HttpSession session
    ) {
        Users users = (Users) session.getAttribute("userLogged");
        GioHang gioHang = gioHangRepository.findByUsers(users);
        List<GioHangChiTiet> list = gioHangChiTietRepository.findByIdGioHang(gioHang);
        Integer soLuong = 0;
        for(GioHangChiTiet ghct : list){
            soLuong += Integer.parseInt(String.valueOf(ghct.getSoLuong()));
        }
        session.setAttribute("soLuong", soLuong);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<SanPham> page = sanPhamRepository.findAll(pageable);
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("list", page.getContent());
        return "page/user/home";
    }

    @GetMapping("/listSP")
    public String producCatetPage(Model model,
                              @RequestParam(name = "pageSize", defaultValue = "9") Integer pageSize,
                              @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                              HttpSession session
    ) {
        List<LoaiSP> listLoai = loaiSPRepository.findAll();
        Users users = (Users) session.getAttribute("userLogged");
        GioHang gioHang = gioHangRepository.findByUsers(users);
        List<GioHangChiTiet> list = gioHangChiTietRepository.findByIdGioHang(gioHang);
        Integer soLuong = 0;
        for(GioHangChiTiet ghct : list){
            soLuong += Integer.parseInt(String.valueOf(ghct.getSoLuong()));
        }
        session.setAttribute("soLuong", soLuong);
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
//        Page<SanPham> page = sanPhamRepository.findAll(pageable);
        List<SanPham> listSP = sanPhamRepository.findAll();
        model.addAttribute("list", listSP);
        model.addAttribute("listLoai", listLoai);
        return "page/user/product";
    }

    @GetMapping("/detail/{id}")
    public String detailPage(Model model,
                             @PathVariable(name = "id") String productId) {
        model.addAttribute("detailProduct", sanPhamRepository.findById(productId).get());
        return "page/user/chi-tiet-sp";
    }

    @GetMapping("/listSP/{ten}")
    public String productPageByCate(Model model,
                                    @PathVariable("ten") String ten
                                    ){
        LoaiSP loaiSP = loaiSPRepository.findbyTen(ten);
        List<LoaiSP> listLoai = loaiSPRepository.findAll();
        List<SanPham> list = sanPhamRepository.getProductByCate(loaiSP);
        model.addAttribute("listLoai", listLoai);
        model.addAttribute("list", list);
        return "page/user/product";
    }
}
