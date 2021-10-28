package com.andrei.stepantcev.messenger.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Profile {
    private final String fullName;
    private final String login;
    private final Integer age;
    private final String sex;
    private final String city;
    private final String interests;
}