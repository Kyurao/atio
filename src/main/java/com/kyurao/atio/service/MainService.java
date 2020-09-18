package com.kyurao.atio.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class MainService {

    public void addMainInfo(Model model, String name) {
        model.addAttribute("name", name);
    }
}