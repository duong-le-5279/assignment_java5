package com.example.asm.controllers.admin;

import com.example.asm.entity.LoaiSP;
import com.example.asm.entity.SanPham;
import com.example.asm.repostories.SanPhamRepository;
import com.example.asm.services.LoaiSPService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("san-pham")
public class SanPhamController {
    @Autowired
    SanPhamRepository sanPhamRepository;

    @Autowired
    LoaiSPService loaiSPService;

    @GetMapping("/list-san-pham")
    public String ListPage(Model model) {
        List<SanPham> listSanPham = sanPhamRepository.findAll();
        model.addAttribute("dsSanPham", listSanPham);
        return "page/admin/san-pham/list";
    }

    @GetMapping("/add-san-pham")
    public String getAddForm(Model model) {
        List<LoaiSP> dsLoaiSP = loaiSPService.findAll();
        model.addAttribute("dsLoaiSP", dsLoaiSP);
        model.addAttribute("product", new SanPham());
        return "page/admin/san-pham/add";
    }

    @Value("${upload.path}")
    private String pathFolder;

    @PostMapping("/add-san-pham")
    public String checkAdd(Model model,
                           @Valid @ModelAttribute("product") SanPham sanPham,
                           BindingResult result,
                           @RequestParam("file") MultipartFile file
                           ) {
        if(result.hasErrors()){
            return "page/admin/san-pham/add";
        }
//        SanPham sp = sanPhamRepository.findByMa(sanPham.getMa());
//        if(sp.getMa().equals(sanPham.getMa())){
//            System.out.println("hi");
//            result.rejectValue("ma", "error.ma", "ma da ton tai");
//            return "page/admin/san-pham/add";
//        }
//        if(sanPham.getGiaNhap() == "")
        try {
            if(!file.isEmpty()){
                byte[] bytes = file.getBytes();
                Path path = Paths.get(pathFolder + file.getOriginalFilename());
                Files.write(path, bytes);
                sanPham.setImage(file.getOriginalFilename());

                Date ngayNhap = new Date();
                sanPham.setNgayNhap(ngayNhap);

                sanPhamRepository.save(sanPham);
                System.out.println(sanPham.getLoaiSP());
                return "redirect:/san-pham/list-san-pham";
            }
        }catch (Exception e){
            System.out.println("Lỗi ở thêm sp");
        }
        return "page/admin/san-pham/add";
    }

    @GetMapping("/delete-san-pham/{id}")
    public String delete(Model model,
                         @PathVariable("id") String id) {
        sanPhamRepository.deleteById(id);
        return "redirect:/san-pham/list-san-pham";
    }

    @GetMapping("/detail-san-pham/{id}")
    public String detail(Model model,
                         @PathVariable("id") String id) {
        SanPham sanPham = sanPhamRepository.findById(id).get();
        List<LoaiSP> dsLoaiSP = loaiSPService.findAll();
        model.addAttribute("dsLoaiSP", dsLoaiSP);
        model.addAttribute("sanPham", sanPham);
        model.addAttribute("sanPhamUpdate", new SanPham());
        return "page/admin/san-pham/detail";
    }

    @PostMapping("/update-san-pham/{id}")
    public String checkUpdate(Model model,
                           @ModelAttribute("sanPhamUpdate") SanPham sanPham,
                           @RequestParam("file") MultipartFile file) {
        try {
            if(!file.isEmpty()){
                byte[] bytes = file.getBytes();
                Path path = Paths.get(pathFolder + file.getOriginalFilename());
                Files.write(path, bytes);
                sanPham.setImage(file.getOriginalFilename());

                Date ngayNhap = new Date();
                sanPham.setNgayNhap(ngayNhap);

                sanPhamRepository.save(sanPham);
                return "redirect:/san-pham/list-san-pham";
            }
        }catch (Exception e){
            System.out.println("Lỗi ở sua sp");
        }
        return "page/admin/san-pham/add";
    }
}
