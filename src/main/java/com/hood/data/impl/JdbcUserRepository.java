package com.hood.data.impl;

import com.hood.data.UserRepository;
import com.hood.pojo.HoodUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author xueta on 2016/8/8 11:44.
 */
@Repository
public class JdbcUserRepository implements UserRepository {
    private JdbcOperations jdbcOperations;

    @Autowired
    public JdbcUserRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public HoodUser save(HoodUser hoodUser) {
        jdbcOperations.update("INSERT INTO user (username, password, email) VALUES (?, ?, ?)", hoodUser.getUsername(), hoodUser.getPassword(), hoodUser.getEmail());
        return hoodUser;
    }

    public HoodUser findByUsername(String username) {
        try {
            HoodUser user = jdbcOperations.queryForObject("SELECT id, username, password, email FROM user where username = ?", new UserRowMapper(), username);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static class UserRowMapper implements RowMapper<HoodUser> {
        public HoodUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new HoodUser(rs.getLong("id"), rs.getString("username"), null, rs.getString("email"));
        }
    }
}
