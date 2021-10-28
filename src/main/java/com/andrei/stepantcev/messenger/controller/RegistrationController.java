package com.andrei.stepantcev.messenger.controller;

import com.andrei.stepantcev.messenger.model.RegistrationForm;
import com.andrei.stepantcev.messenger.service.RegistrationService;
import com.andrei.stepantcev.messenger.service.SexTypeService;
import com.andrei.stepantcev.messenger.service.UserService;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final SexTypeService sexTypeService;
    private final UserService userService;
    private final RegistrationService registrationService;

    @GetMapping("/registration")
    public String getRegistrationPage(final Model model) {

        val isRegistrationFormExists = model.containsAttribute("registrationForm");

        if (!isRegistrationFormExists) {
            val registrationForm = new RegistrationForm();
            model.addAttribute("registrationForm", registrationForm);
        }

        val sexTypes = sexTypeService.getSexTypes();
        model.addAttribute("sexTypes", sexTypes);

        return "pages/registration";
    }

    @PostMapping("/registration/process")
    public String registrationProcess(final @ModelAttribute RegistrationForm registrationForm, final RedirectAttributes redirectAttributes) {

        val login = registrationForm.getLogin();
        val isLoginExists = userService.checkUserExists(login);

        if (isLoginExists) {
            redirectAttributes.addFlashAttribute("error", "Пользователь с таким логином уже зарегистрирован");
            redirectAttributes.addFlashAttribute("registrationForm", registrationForm);
            return "redirect:/registration";
        }

        registrationService.register(registrationForm);

        return "redirect:/login";
    }

    @GetMapping("/registration/generate")
    @ResponseStatus(HttpStatus.OK)
    public void generateRegistrations() {
        final IntConsumer generateRegistrations = (index) -> registrationService.generateRegistrations();
        IntStream.range(0, 100).forEach(generateRegistrations);
    }
}