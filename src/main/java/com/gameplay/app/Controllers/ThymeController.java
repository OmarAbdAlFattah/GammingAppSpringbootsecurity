package com.gameplay.app.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymeController {
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
