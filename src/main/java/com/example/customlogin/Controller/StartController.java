package com.example.customlogin.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {
    @GetMapping({"/", "/index", "/main", "/start"})
    public String start(Model model) throws Exception {

        return "index";
    }
}
