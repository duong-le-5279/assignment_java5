package com.example.asm.controllers.admin;

import com.example.asm.entity.LoaiSP;
import com.example.asm.repostories.LoaiSPRepository;
import com.example.asm.services.LoaiSPService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/loai-sp")
public class LoaiSPController {

    @Autowired
    LoaiSPService loaiSPService;

    @Autowired
    LoaiSPRepository loaiSPRepository;

    @GetMapping("/list-loai-sp")
    public String ListPage(Model model) {
        List<LoaiSP> listLoaiSP = loaiSPService.findAll();
        model.addAttribute("dsLoaiSP", listLoaiSP);
        return "page/admin/loai-sp/list";
    }

    @GetMapping("/add-loai-sp")
    public String getAddForm(Model model) {

        model.addAttribute("loaiSP", new LoaiSP());
        return "page/admin/loai-sp/add";
    }

    @PostMapping("/add-loai-sp")
    public String checkAdd(Model model,
                           @Valid @ModelAttribute("loaiSP") LoaiSP loaiSP,
                           BindingResult result) {
        if(result.hasErrors()){
            return "page/admin/loai-sp/add";
        }
        LoaiSP checkLSP = loaiSPRepository.findByMa(loaiSP.getMa());
        if(checkLSP.getMa().equals(loaiSP.getMa())){
//            model.addAttribute("errorMa", "Mã đã tồn tại");
            result.rejectValue("ma","error.ma", "Mã khong duoc trung");
            return "page/admin/loai-sp/add";

        }
        Date ngayTao = new Date();
        loaiSP.setNgayTao(ngayTao);
        loaiSPService.save(loaiSP);
        return "redirect:/loai-sp/list-loai-sp";
    }

    @GetMapping("/delete-loai-sp/{id}")
    public String delete(Model model,
                         @PathVariable("id") String id) {
        loaiSPService.deleteById(id);
        return "redirect:/loai-sp/list-loai-sp";
    }

    @GetMapping("/detail-loai-sp/{id}")
    public String detail(Model model,
                         @PathVariable("id") String id) {
        LoaiSP loaiSP = loaiSPService.findById(id);
        model.addAttribute("loaiSP", loaiSP);
        model.addAttribute("loaiSp", new LoaiSP());
        System.out.println(1);
        return "/page/admin/loai-sp/detail";
    }

    @PostMapping("/update-loai-sp/{id}")
    public String update(Model model,
                         @ModelAttribute("LoaiSP") LoaiSP loaiSP,
                         @PathVariable("id") String id) {
        LoaiSP loaiSPUpdate = loaiSPService.findById(id);
        loaiSP.setId(id);
        loaiSP.setNgayTao(loaiSPUpdate.getNgayTao());
        loaiSPService.save(loaiSP);
        return "redirect:/loai-sp/list-loai-sp";
    }
}
