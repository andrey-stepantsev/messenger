package com.andrei.stepantcev.messenger.service.impl;

import com.andrei.stepantcev.messenger.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public boolean checkUserExists(final String login) {

        val query = "SELECT COUNT(*) FROM USER WHERE LOGIN = ?";
        val parameters = new Object[] { login };

        val result = jdbcTemplate.queryForObject(query, Integer.class, parameters);

        return result != null && result > 0;
    }
}