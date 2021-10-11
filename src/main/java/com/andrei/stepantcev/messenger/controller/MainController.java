package com.andrei.stepantcev.messenger.controller;

import com.andrei.stepantcev.messenger.model.RegistrationForm;
import com.andrei.stepantcev.messenger.service.RegistrationService;
import com.andrei.stepantcev.messenger.service.SexTypeService;
import com.andrei.stepantcev.messenger.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final SexTypeService sexTypeService;
    private final UserService userService;
    private final RegistrationService registrationService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(final Model model) {

        val registrationForm = new RegistrationForm();
        val sexTypes = sexTypeService.getSexTypes();

        model.addAttribute(registrationForm);
        model.addAttribute("sexTypes", sexTypes);

        return "registration";
    }

    @PostMapping("/registration/create")
    public String createRegistration(final @ModelAttribute RegistrationForm registrationForm, final Model model) {

        val login = registrationForm.getLogin();
        val isLoginExists = userService.checkUserExists(login);

        if (isLoginExists) {
            val sexTypes = sexTypeService.getSexTypes();
            model.addAttribute("sexTypes", sexTypes);
            model.addAttribute("error", "Пользователь с таким логином уже зарегистрирован");
            return "registration";
        }

        registrationService.register(registrationForm);

        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String getProfilePage() {
        return "profile";
    }
}