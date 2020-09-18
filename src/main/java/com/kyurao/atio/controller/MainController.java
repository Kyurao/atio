package com.kyurao.atio.controller;

import com.kyurao.atio.service.MainService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/")
public class MainController {

    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping
    public String getMainPage(@RequestParam(defaultValue = "World") String name,
                              Model model) {
        mainService.addMainInfo(model, name);
        return "index";
    }
}
