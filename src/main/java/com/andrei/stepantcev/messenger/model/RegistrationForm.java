package com.andrei.stepantcev.messenger.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationForm {
    private String firstName;
    private String lastName;
    private Integer age;
    private SexTypeRow sexType;
    private String city;
    private String interests;
    private String login;
    private String password;
}