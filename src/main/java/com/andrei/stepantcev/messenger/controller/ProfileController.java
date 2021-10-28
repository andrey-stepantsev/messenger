package com.andrei.stepantcev.messenger.controller;

import com.andrei.stepantcev.messenger.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/profile")
    public String getProfilePage(final @AuthenticationPrincipal UserDetails userDetails, final Model model) {

        val login = userDetails.getUsername();
        val profile = userService.getProfile(login);
        val friends = userService.getFriends(login);
        val friendsCount = friends.size();

        model.addAttribute("profile", profile);
        model.addAttribute("friends", friends);
        model.addAttribute("friendsCount", friendsCount);

        return "pages/profile";
    }
}