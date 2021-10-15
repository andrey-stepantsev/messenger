package com.andrei.stepantcev.messenger.service.impl;

import com.andrei.stepantcev.messenger.mapper.SexTypeRowMapper;
import com.andrei.stepantcev.messenger.model.SexTypeRow;
import com.andrei.stepantcev.messenger.service.SexTypeService;
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
public class SexTypeServiceImpl implements SexTypeService {

    private final JdbcTemplate jdbcTemplate;
    private final SexTypeRowMapper sexTypeRowMapper;

    @Override
    @SneakyThrows
    @Transactional(readOnly = true)
    public List<SexTypeRow> getSexTypes() {
        val query = SQLUtils.getQuery("sexTypes");
        return jdbcTemplate.query(query, sexTypeRowMapper);
    }
}