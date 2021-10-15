package com.andrei.stepantcev.messenger.service.impl;

import com.andrei.stepantcev.messenger.model.RegistrationForm;
import com.andrei.stepantcev.messenger.service.RegistrationService;
import com.andrei.stepantcev.messenger.utils.SQLUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    @Override
    @SneakyThrows
    @Transactional
    public void register(final RegistrationForm registrationForm) {
        val firstName = registrationForm.getFirstName();
        val lastName = registrationForm.getLastName();
        val age = registrationForm.getAge();
        val sexTypeId = registrationForm.getSexType().getId();
        val city = registrationForm.getCity();
        val interests = registrationForm.getInterests();
        val login = registrationForm.getLogin();
        val password = registrationForm.getPassword();
        val passwordEncoded = passwordEncoder.encode(password);
        val query = SQLUtils.getQuery("registration");
        jdbcTemplate.update(query, firstName, lastName, age, sexTypeId, city, interests, login, passwordEncoded);
    }
}