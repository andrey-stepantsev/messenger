package com.andrei.stepantcev.messenger.mapper;

import com.andrei.stepantcev.messenger.model.UserRow;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.val;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class UserRowMapper implements RowMapper<UserRow> {

    @Override
    public UserRow mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
        val id = resultSet.getInt("ID");
        val firstName = resultSet.getString("FIRST_NAME");
        val lastName = resultSet.getString("LAST_NAME");
        val login = resultSet.getString("LOGIN");
        val password = resultSet.getString("PASSWORD");
        val city = resultSet.getString("CITY");
        val interests = resultSet.getString("INTERESTS");
        return new UserRow(id, firstName, lastName, login, password, city, interests);
    }
}