package com.example.asm.controllers.user;

import com.example.asm.entity.*;
import com.example.asm.repostories.*;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/hoa-don")
public class HoaDonController {

    @Autowired
    GioHangRepository gioHangRepository;

    @Autowired
    GioHangChiTietRepository gioHangChiTietRepository;

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/show")
    public String orderPage(Model model,
                            HttpSession session) {
        Users users = (Users) session.getAttribute("userLogged");
        GioHang gioHang = gioHangRepository.findByUsers(users);
        List<GioHangChiTiet> list = gioHangChiTietRepository.findByIdGioHang(gioHang);
        if(list.size() == 0){
            return "redirect:/gio-hang/hien-thi";
        }
        Long price = Long.valueOf(0);
        for (GioHangChiTiet ghct : list) {
            price += ghct.getSoLuong() * ghct.getId().getSanPham().getGiaBan();
            model.addAttribute("messsage", "số lượng sản phẩm trong kho k đủ");
        }
        model.addAttribute("list", list);
        model.addAttribute("tongGia", price);
        return "page/user/hoa-don";
    }

    @GetMapping("/thanh-toan")
    public String thanhToan(Model model,
                            HttpSession session) {
        Users users = (Users) session.getAttribute("userLogged");
        GioHang gh = gioHangRepository.findByUsers(users);
        List<GioHangChiTiet> list = gioHangChiTietRepository.findAll();
        String ma = RandomStringUtils.randomAlphabetic(8);
        Date date = new Date();
        String trangThai = "chờ";
        Long tongGia = Long.valueOf("0");
        for (GioHangChiTiet ghct : list) {
            tongGia += ghct.getSoLuong() * ghct.getId().getSanPham().getGiaBan();
            if(ghct.getSoLuong() > ghct.getId().getSanPham().getSoLuong()){
                return "redirect:/hoa-don/show";
            }
        }
        session.setAttribute("soLuong", 0);
        gioHangChiTietRepository.deleteByIdGioHang(gh);
        HoaDon hoaDon = new HoaDon(users, ma, trangThai, date, date, tongGia);
        HoaDonChiTiet hoaDonChiTiet;
        hoaDonRepository.save(hoaDon);
        for (GioHangChiTiet ghct : list) {
            HoaDonChiTietId hoaDonChiTietId = new HoaDonChiTietId(hoaDon, ghct.getId().getSanPham());
            Long price = ghct.getSoLuong() * ghct.getId().getSanPham().getGiaBan();
            hoaDonChiTiet = new HoaDonChiTiet(hoaDonChiTietId, ghct.getSoLuong(), price);

            SanPham sp = ghct.getId().getSanPham();
            sp.setSoLuong(sp.getSoLuong() - ghct.getSoLuong());
            sanPhamRepository.save(sp);
            hoaDonChiTietRepository.save(hoaDonChiTiet);
        }

        return "page/user/thanh-toan";
    }

    @GetMapping("/danh-sach-da-mua")
    public String listOrderPage(Model model,
                                HttpSession session) {
        Users users = (Users) session.getAttribute("userLogged");

        List<HoaDon> listHoaDon = hoaDonRepository.getOrderByUserId(users.getId());
        List<HoaDonChiTiet> listHdct = new ArrayList<>();
        for (HoaDon hd : listHoaDon) {
           listHdct = hoaDonChiTietRepository.findByIdHoaDon(hd);
        }

        model.addAttribute("dsHoaDon", listHoaDon);
        return "/page/user/danh-sach-hoa-don";
    }

    @GetMapping("/chi-tiet/{id}")
    public String orderDetailPage(Model model,
                                  @PathVariable("id") String id) {
        List<HoaDonChiTiet> listSP = hoaDonRepository.getProductByIdOrder(id);
        Long tongGia = Long.valueOf("0");
        Long giaBan = Long.valueOf("0");
        for (HoaDonChiTiet hdct : listSP) {
            tongGia += hdct.getGia();
        }

        model.addAttribute("tongGia", tongGia);
        model.addAttribute("listSP", listSP);
        return "/page/user/detail-hoa-don";
    }
}
