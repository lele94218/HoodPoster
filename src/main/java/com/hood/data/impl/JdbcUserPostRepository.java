package com.hood.data.impl;

import com.hood.data.UserPostRepository;
import com.hood.pojo.UserPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author taoranxue on 8/29/16 7:14 PM.
 */
@Repository
public class JdbcUserPostRepository implements UserPostRepository {
    private JdbcOperations jdbcOperations;

    @Autowired
    public JdbcUserPostRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public UserPost save(UserPost userPost) {
        jdbcOperations.update("INSERT INTO user_post (title, date, userName, content) VALUES (?, ?, ?, ?)",
                userPost.getTitle(), userPost.getDate(), userPost.getUserName(), userPost.getContent());
        return userPost;
    }

    public UserPost findById(Long id) {
        try {
            return jdbcOperations.queryForObject("SELECT id, title, date, userName, content FROM user_post where id = ?",
                    new JdbcUserPostRepository.UserPostRowMapper(), id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<UserPost> findByUserName(String userName) {
        // TODO
        return null;
    }

    private static class UserPostRowMapper implements RowMapper<UserPost> {
        public UserPost mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new UserPost(rs.getLong("id"), rs.getString("title"), rs.getDate("date"),
                    rs.getString("username"), rs.getString("content"));
        }
    }


}
