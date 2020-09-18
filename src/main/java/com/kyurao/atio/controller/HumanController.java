package com.kyurao.atio.controller;

import com.kyurao.atio.domain.Human;
import com.kyurao.atio.service.HumanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/human")
@RequiredArgsConstructor
public class HumanController {

    private final HumanService humanService;

    @GetMapping
    public String getHumanPage(Model model) {
        model.addAttribute("human", new Human());
        return "human/human";
    }

    @PostMapping("/create/simple")
    public String createHuman(Human human) {
        humanService.createHuman(human);

        return "human/human";
    }

    @PostMapping("/create")
    public String createByPostHuman(Human human) {
        humanService.createHuman(human);
        return "human/human";
    }

    @GetMapping("/{id}")
    public String getHuman(@PathVariable Long id) {
        return humanService.getHumanById(id);
    }

    @GetMapping("/all")
    public String getPeople(Model model) {
        model.addAttribute("people", humanService.getAllPeople());
        return "human/people";
    }

    @GetMapping("/delete/{id}")
    public void deleteHuman(@PathVariable Long id) {
        humanService.removeHumanById(id);
    }

    @GetMapping("/update/{id}")
    public String updateHuman(@PathVariable Long id,
                            @RequestParam(required = false) String name,
                            @RequestParam Integer age) {

        return humanService.updateHumanById(id, name, age);
    }
}
