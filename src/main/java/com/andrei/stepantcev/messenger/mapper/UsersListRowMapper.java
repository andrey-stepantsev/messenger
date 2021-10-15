package com.andrei.stepantcev.messenger.mapper;

import com.andrei.stepantcev.messenger.model.UsersListRow;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.val;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class UsersListRowMapper implements RowMapper<UsersListRow> {

    @Override
    public UsersListRow mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
        val id = resultSet.getInt("ID");
        val fullName = resultSet.getString("FULL_NAME");
        val login = resultSet.getString("LOGIN");
        return new UsersListRow(id, fullName, login);
    }
}