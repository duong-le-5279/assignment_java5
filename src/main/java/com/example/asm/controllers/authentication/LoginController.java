package com.example.asm.controllers.authentication;

import com.example.asm.entity.Users;
import com.example.asm.repostories.UserRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UserRepository usersRepository;

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        model.addAttribute("user", new Users());
        return "/page/authentication/login";
    }

    @PostMapping("/home")
    public String checkLogin(Model model,
                             @Valid
                             @ModelAttribute("user") Users users,
                             BindingResult result,
                             HttpSession session,
                             RedirectAttributes attributes) {
        if(result.hasErrors()){
            return "/page/authentication/login";
        }
        Users userFromDB = usersRepository.findUserByUsernameAndPassword(users.getUsername(), users.getPassword());
        if(userFromDB != null){
//            session.setAttribute("", userFromDB);
            if(userFromDB.isRole()){
                session.setAttribute("userLogged", userFromDB);
                return "redirect:/product/list";
            }else{
                session.setAttribute("adminLogged", userFromDB);
                return "page/admin/index";
            }
        }
//        login failed
        model.addAttribute("userError", users.getUsername());
        model.addAttribute("passError", users.getPassword());
        model.addAttribute("message", "Sai cmnr b ơi");
        return "/page/authentication/login";
    }
}
