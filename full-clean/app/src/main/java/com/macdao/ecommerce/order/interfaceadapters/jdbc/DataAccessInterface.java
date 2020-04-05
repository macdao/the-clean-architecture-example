package com.macdao.ecommerce.order.interfaceadapters.jdbc;

import java.util.*;

public interface DataAccessInterface {
    Map<String, Object> query(String sql, Map<String, ?> paramMap);

    List<Map<String, Object>> queryForList(String sql, Map<String, ?> paramMap);

    int update(String sql, Map<String, ?> paramMap);

    int update(String sql, List<Map<String, ?>> batchValues);
}
