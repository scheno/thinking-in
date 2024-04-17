package com.schening.mybatis;

import com.schening.app.User;

import java.sql.*;

public class BasedExecutor implements Executor {

    @Override
    public <T> T query(String sql, Object parameter) {
        try {
            Connection connection = DriverManager.getConnection("", "", "");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(String.format(sql, parameter));
            rs.next();
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            return (T) user;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
