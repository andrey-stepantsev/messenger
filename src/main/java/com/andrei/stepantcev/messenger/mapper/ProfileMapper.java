package com.andrei.stepantcev.messenger.mapper;

import com.andrei.stepantcev.messenger.model.Profile;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.val;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class ProfileMapper implements RowMapper<Profile> {

    @Override
    public Profile mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
        val fullName = resultSet.getString("FULL_NAME");
        val login = resultSet.getString("LOGIN");
        val age = resultSet.getInt("U.AGE");
        val sex = resultSet.getString("ST.VALUE");
        val city = resultSet.getString("U.CITY");
        val interests = resultSet.getString("U.INTERESTS");
        return new Profile(fullName, login, age, sex, city, interests);
    }
}