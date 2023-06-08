package com.example.asm.controllers.admin;

import com.example.asm.entity.GioHang;
import com.example.asm.entity.LoaiSP;
import com.example.asm.entity.Users;
import com.example.asm.repostories.GioHangChiTietRepository;
import com.example.asm.repostories.GioHangRepository;
import com.example.asm.repostories.UserRepository;
import com.example.asm.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    GioHangRepository gioHangRepository;

    @Autowired
    GioHangChiTietRepository gioHangChiTietRepository;

    @GetMapping("/list-user")
    public String ListPage(Model model) {
        List<Users> listUser = userRepository.findAll();
        model.addAttribute("dsUser", listUser);
        return "/page/admin/user/list";
    }

    @GetMapping("/add-user")
    public String getAddForm(Model model) {
        return "/page/admin/user/add";
    }

    @PostMapping("/add-user")
    public String checkAdd(Model model,
                           @ModelAttribute("users") Users users,
                           HttpSession session) {
        Date ngayTao = new Date();
        users.setNgayTao(ngayTao);
        GioHang cartUser = new GioHang();
        cartUser.setUsers(users);
        cartUser.setNgayTao(ngayTao);

        userRepository.save(users);
        if(users.isRole()){
            gioHangRepository.save(cartUser);
        }
        return "/page/admin/user/add";
    }

    @GetMapping("/delete-user/{id}")
    public String delete(Model model,
                         @PathVariable("id") String id) {
        Users users = userRepository.findById(id).get();
        gioHangRepository.findByUsers(users);
        userRepository.deleteById(id);
        return "redirect:/user/list-user";
    }

    @GetMapping("/detail-user/{id}")
    public String detail(Model model,
                         @PathVariable("id") String id) {
        Users users = userRepository.findById(id).get();
        model.addAttribute("users", users);
        model.addAttribute("userUpdate", new Users());
        System.out.println(1);
        return "/page/admin/user/detail";
    }

    @PostMapping("/update-user/{id}")
    public String update(Model model,
                         @ModelAttribute("userUpdate") Users users,
                         @PathVariable("id") String id) {
        users.setId(id);
        userRepository.save(users);
        return "redirect:/user/list-user";
    }
}
