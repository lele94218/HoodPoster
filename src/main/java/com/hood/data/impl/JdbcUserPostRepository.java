package com.hood.data.impl;

import com.hood.data.UserPostRepository;
import com.hood.pojo.UserPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
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

    public UserPost save(final UserPost userPost) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement ps = con.prepareStatement(
                                "INSERT INTO user_post (title, date, userName, content) VALUES (?, ?, ?, ?)",
                                new String[]{"id"});
                        ps.setString(1, userPost.getTitle());
//                        ps.setDate(2, new Date(userPost.getDate().getTime()));
                        ps.setTimestamp(2, new Timestamp(userPost.getDate().getTime()));
                        ps.setString(3, userPost.getUserName());
                        ps.setString(4, userPost.getContent());
                        return ps;
                    }
                }, keyHolder);
        jdbcOperations.update("INSERT INTO user_post (title, date, userName, content) VALUES (?, ?, ?, ?)",
                userPost.getTitle(), userPost.getDate(), userPost.getUserName(), userPost.getContent());
        userPost.setId(keyHolder.getKey().longValue());
        return userPost;
    }


    public UserPost findById(Long id) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
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
            return new UserPost(rs.getLong("id"), rs.getString("title"), rs.getTimestamp("date"),
                    rs.getString("username"), rs.getString("content"));
        }
    }


}
