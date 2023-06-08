package com.example.asm.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

@Controller
@Repository("/hoa-don")
public class HoaDonController {

    public String orderPage(Model model){

        return "page/user/hoa-don";
    }
}
