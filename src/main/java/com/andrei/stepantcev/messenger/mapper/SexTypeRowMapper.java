package com.andrei.stepantcev.messenger.mapper;

import com.andrei.stepantcev.messenger.model.SexTypeRow;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.val;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class SexTypeRowMapper implements RowMapper<SexTypeRow> {

    @Override
    public SexTypeRow mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
        val id = resultSet.getInt("ID");
        val value = resultSet.getString("VALUE");
        return new SexTypeRow(id, value);
    }
}