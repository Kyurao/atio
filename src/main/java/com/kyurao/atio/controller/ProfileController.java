package com.kyurao.atio.controller;

import com.kyurao.atio.dto.ProfileDto;
import com.kyurao.atio.service.ProfileService;
import com.kyurao.atio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    private final UserService userService;


    @GetMapping("/profile")
    public ModelAndView getProfile() {
        var view = new ModelAndView("profile");
        view.addObject("title", "My Profile");
        var user = userService.getUser();
        view.addObject("profile", profileService.toProfileDto(user));

        return view;
    }

//    @ModelAttribute("profile")
//    ProfileDto profileDto() {
//        return new ProfileDto();
//    }

    @PostMapping("/profile")
    public ModelAndView postProfile(@Valid @ModelAttribute("profile") ProfileDto dto,
                                    BindingResult bindingResult) {
        var view = new ModelAndView("profile");
        view.addObject("title", "My Profile");
        if (!bindingResult.hasErrors()) {
            var updated = profileService.editUser(dto);
            view.addObject("profile", profileService.toProfileDto(updated));
        }

        return view;
    }
}
