package com.andrei.stepantcev.messenger.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsersListRow {
    private final Integer id;
    private final String fullName;
    private final String login;
}