package com.macdao.ecommerce.order.details.jdbc;

import com.macdao.ecommerce.order.interfaceadapters.jdbc.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public class DataAccess implements DataAccessInterface {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DataAccess(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Map<String, Object> query(String sql, Map<String, ?> paramMap) {
        return namedParameterJdbcTemplate.queryForMap(sql, paramMap);
    }

    @Override
    public List<Map<String, Object>> queryForList(String sql, Map<String, ?> paramMap) {
        return namedParameterJdbcTemplate.queryForList(sql, paramMap);
    }

    @Override
    public int update(String sql, Map<String, ?> paramMap) {
        return namedParameterJdbcTemplate.update(sql, paramMap);
    }

    @Override
    public int update(String sql, List<Map<String, ?>> batchValues) {
        var ints = namedParameterJdbcTemplate.batchUpdate(sql, SqlParameterSourceUtils.createBatch(batchValues));
        return Arrays.stream(ints).sum();
    }
}
