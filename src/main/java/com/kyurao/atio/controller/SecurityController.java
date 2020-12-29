package com.kyurao.atio.controller;

import com.kyurao.atio.dto.RegistrationReq;
import com.kyurao.atio.service.AuthService;
import com.kyurao.atio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class SecurityController {

    private final AuthService authService;
    private final UserService userService;

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("security/login");
    }

    @GetMapping("/register")
    public ModelAndView register() {
        var view = new ModelAndView("security/registration");
        view.addObject("title", "Registration");
        return view;
    }

    @PostMapping("/register")
    public ModelAndView handleRegister(@Valid @ModelAttribute("registrationReq") RegistrationReq req,
                                       BindingResult bindingResult) {
        var view = new ModelAndView("security/registration");
        if (!bindingResult.hasErrors()) {
            authService.register(req);
            view.addObject("email", req.getEmail());
        }

        return view;
    }

    @ModelAttribute("registrationReq")
    RegistrationReq registrationDto() {
        return new RegistrationReq();
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Activation code is not found!");
        }

        return "security/login";
    }
}
