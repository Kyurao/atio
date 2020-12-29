package com.kyurao.atio.controller;

import com.kyurao.atio.dto.LotReq;
import com.kyurao.atio.dto.LotRes;
import com.kyurao.atio.service.LotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class LotController {

    private final LotService lotService;

    @GetMapping("/my-lots")
    public ModelAndView getUserLots() {
        var view = new ModelAndView("userLots");
        view.addObject("title", "My Lots");

        return view;
    }

    @GetMapping("/add-lot")
    public ModelAndView getLot() {
        var view = new ModelAndView("lotEdit");
        view.addObject("lot", new LotReq());
        view.addObject("title", "Lot's Editor");

        return view;
    }

    @PostMapping("/add-lot")
    public ModelAndView addNewLot(@Valid @ModelAttribute("lot") LotReq dto,
                                  BindingResult bindingResult) {
        var view = new ModelAndView("lotEdit");
        view.addObject("title", "Lot's Editor");
        lotService.create(dto);

        return view; // FIXME: 16.12.2020 redirect to all lots page
    }

    @GetMapping("/lot/active/all")
    public ModelAndView getActiveLots() {
        var view = new ModelAndView("userLots");
        view.addObject("title", "Lots");
        List<LotRes> result = lotService
                .getAllActive()
                .stream()
                .map(lotService::toLotResponse)
                .collect(Collectors.toList());
        view.addObject("lots", result);

        return view;
    }
}
