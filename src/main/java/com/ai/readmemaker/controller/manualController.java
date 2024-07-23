package com.ai.readmemaker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manual")
public class manualController {

    @GetMapping("/start")
    public String startPage() {
        return "manual/start";
    }
}
