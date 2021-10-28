package com.andrei.stepantcev.messenger.service.impl;

import com.andrei.stepantcev.messenger.model.RegistrationForm;
import com.andrei.stepantcev.messenger.service.RegistrationService;
import com.andrei.stepantcev.messenger.utils.LoginUtils;
import com.andrei.stepantcev.messenger.utils.SQLUtils;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import lombok.var;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final Faker faker = new Faker();

    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    @Override
    @SneakyThrows
    @Transactional
    public void register(final RegistrationForm registrationForm) {

        val query = SQLUtils.getQuery("registration");

        val firstName = registrationForm.getFirstName();
        val lastName = registrationForm.getLastName();
        val age = registrationForm.getAge();
        val sexTypeId = registrationForm.getSexType().getId();
        val city = registrationForm.getCity();
        val interests = registrationForm.getInterests();
        val login = registrationForm.getLogin();
        val password = registrationForm.getPassword();
        val passwordEncoded = passwordEncoder.encode(password);

        jdbcTemplate.update(query, firstName, lastName, age, sexTypeId, city, interests, login, passwordEncoded);
    }

    @Override
    @Async
    @SneakyThrows
    public void generateRegistrations() {

        val query = SQLUtils.getQuery("registration");

        val dataSource = jdbcTemplate.getDataSource();
        val connection = dataSource.getConnection();

        connection.setAutoCommit(false);

        val prepareStatement = connection.prepareStatement(query);
        val password = passwordEncoder.encode("12345");

        for (var i = 0; i < 10_000; i++) {

            val firstName = faker.name().firstName();
            val lastName = faker.name().lastName();
            val login = LoginUtils.generateLogin();

            prepareStatement.setString(1, firstName);
            prepareStatement.setString(2, lastName);
            prepareStatement.setInt(3, 23);
            prepareStatement.setInt(4, 1);
            prepareStatement.setString(5, "Череповец");
            prepareStatement.setString(6, null);
            prepareStatement.setString(7, login);
            prepareStatement.setString(8, password);

            prepareStatement.addBatch();
        }

        prepareStatement.executeBatch();
        prepareStatement.clearBatch();

        connection.commit();

        prepareStatement.close();
        connection.close();
    }
}