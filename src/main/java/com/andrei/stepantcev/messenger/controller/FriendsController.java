package com.andrei.stepantcev.messenger.controller;

import com.andrei.stepantcev.messenger.model.FriendRequestBody;
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
    public List<UsersListRow> searchNewFriends(final @RequestParam String searchFirstname, final @RequestParam String searchLastname, final @AuthenticationPrincipal UserDetails userDetails) {
        val userLogin = userDetails.getUsername();
        return userService.searchNewFriends(userLogin, searchFirstname, searchLastname);
    }

    @PostMapping("/friends/add")
    @ResponseStatus(HttpStatus.OK)
    public void addFriend(final @RequestBody FriendRequestBody requestBody, final @AuthenticationPrincipal UserDetails userDetails) {
        val friendId = requestBody.getFriendId();
        val userLogin = userDetails.getUsername();
        userService.addFriend(userLogin, friendId);
    }
}