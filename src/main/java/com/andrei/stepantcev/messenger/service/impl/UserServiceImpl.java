package com.andrei.stepantcev.messenger.service.impl;

import com.andrei.stepantcev.messenger.mapper.ProfileMapper;
import com.andrei.stepantcev.messenger.mapper.UsersListRowMapper;
import com.andrei.stepantcev.messenger.model.Profile;
import com.andrei.stepantcev.messenger.model.UsersListRow;
import com.andrei.stepantcev.messenger.service.UserService;
import com.andrei.stepantcev.messenger.utils.SQLUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JdbcTemplate jdbcTemplate;
    private final ProfileMapper profileMapper;
    private final UsersListRowMapper usersListRowMapper;

    @Override
    @SneakyThrows
    @Transactional(readOnly = true)
    public boolean checkUserExists(final String userLogin) {
        val query = SQLUtils.getQuery("userExists");
        val usersCount = jdbcTemplate.queryForObject(query, Integer.class, userLogin);
        return usersCount != null && usersCount > 0;
    }

    @Override
    @SneakyThrows
    @Transactional(readOnly = true)
    public Profile getProfile(final String userLogin) {
        val query = SQLUtils.getQuery("profile");
        return jdbcTemplate.queryForObject(query, profileMapper, userLogin);
    }

    @Override
    @SneakyThrows
    @Transactional(readOnly = true)
    public List<UsersListRow> getFriends(final String userLogin) {
        val query = SQLUtils.getQuery("friends");
        return jdbcTemplate.query(query, usersListRowMapper, userLogin, userLogin);
    }

    @Override
    @SneakyThrows
    @Transactional(readOnly = true)
    public List<UsersListRow> searchNewFriends(final String userLogin, final String searchLogin) {
        val query = SQLUtils.getQuery("searchNewFriends");
        return jdbcTemplate.query(query, usersListRowMapper, searchLogin, userLogin, userLogin, userLogin);
    }

    @Override
    @SneakyThrows
    @Transactional
    public void addFriend(final String userLogin, final Integer friendId) {
        val query = SQLUtils.getQuery("addFriend");
        jdbcTemplate.update(query, friendId, userLogin);
    }
}