package com.andrei.stepantcev.messenger.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRow {
    private Integer id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String city;
    private String interests;
}