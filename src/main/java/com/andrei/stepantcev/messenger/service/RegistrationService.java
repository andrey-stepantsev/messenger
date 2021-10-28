package com.andrei.stepantcev.messenger.service;

import com.andrei.stepantcev.messenger.model.RegistrationForm;

public interface RegistrationService {

    void register(RegistrationForm registrationForm);

    void generateRegistrations();

}