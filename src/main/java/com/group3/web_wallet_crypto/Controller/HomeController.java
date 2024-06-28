package com.group3.web_wallet_crypto.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // Đánh dấu lớp này là một Controller trong Spring MVC.
public class HomeController {
    @RequestMapping
    public String hello(Model model) {
        return "index";
    }
}
