package com.example.asm.controllers.authentication;

import com.example.asm.entity.GioHang;
import com.example.asm.entity.Users;
import com.example.asm.repostories.GioHangRepository;
import com.example.asm.repostories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GioHangRepository gioHangRepository;

    @GetMapping("/register")
    public String PageRegister(Model model){
        model.addAttribute("users", new Users());
        return "/page/authentication/register";
    }

    @PostMapping("/register")
    public String CheckRegister(Model model,
                                @Valid @ModelAttribute("users")Users users,
                                BindingResult result){
        if(result.hasErrors()){
            model.addAttribute("messageRegister", "Register false");
            return "/page/authentication/register";
        }
        users.setRole(true);
        Date ngayTao = new Date();
        users.setNgayTao(ngayTao);
        GioHang cartUser = new GioHang();
        cartUser.setUsers(users);
        cartUser.setNgayTao(ngayTao);
        userRepository.save(users);
        if(users.isRole()){
            gioHangRepository.save(cartUser);
        }
        return "redirect:/login";
    }
}
