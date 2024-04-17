package com.schening.mybatis;

public class SqlSession {

    private Configuration configuration;

    private Executor executor;

    public SqlSession() {

    }

    public SqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    public <T> T selectOne(String statementId, Object parameter) {
        String sql = Configuration.sqlMappings.getString(statementId);
        if (sql != null && !sql.isEmpty()) {
            return executor.query(sql, parameter);
        }
        return null;
    }

    public <T> T getMapper(Class<T> clazz) {
        return  configuration.getMapper(clazz, this);
    }

}
