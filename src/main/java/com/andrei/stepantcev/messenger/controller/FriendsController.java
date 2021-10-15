package com.andrei.stepantcev.messenger.controller;

import com.andrei.stepantcev.messenger.model.UserRequestBody;
import com.andrei.stepantcev.messenger.model.UsersListRow;
import com.andrei.stepantcev.messenger.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
public class FriendsController {

    private final UserService userService;

    @GetMapping("/friends/search/new")
    @ResponseBody
    public List<UsersListRow> searchUsers(final @RequestParam String searchLogin, final @AuthenticationPrincipal UserDetails userDetails) {
        val userLogin = userDetails.getUsername();
        return userService.searchNewFriends(userLogin, searchLogin);
    }

    @PostMapping("/friends/add")
    @ResponseStatus(HttpStatus.OK)
    public void addFriend(final @RequestBody UserRequestBody requestBody, final @AuthenticationPrincipal UserDetails userDetails) {
        val friendId = requestBody.getUserId();
        val userLogin = userDetails.getUsername();
        userService.addFriend(userLogin, friendId);
    }
}