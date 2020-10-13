package com.kyurao.atio.controller;

import com.kyurao.atio.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;


    @GetMapping
    public String getMainPage(@RequestParam(defaultValue = "World") String name,
                              Model model) {
        mainService.addMainInfo(model, name);
        return "index";
    }
}
