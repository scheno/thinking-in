package com.schening.mybatis;

public interface Executor {
    <T> T query(String sql, Object parameter);

}
