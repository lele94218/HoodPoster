package com.hood.data.impl;

import com.hood.common.Page;
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
                        ps.setTimestamp(2, new Timestamp(userPost.getDate().getTime()));
                        ps.setString(3, userPost.getUserName());
                        ps.setString(4, userPost.getContent());
                        return ps;
                    }
                }, keyHolder);
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

    public Page<UserPost> findAllPage(int pageNumber, int pageSize) {
        int entityCount = jdbcOperations.queryForObject("SELECT count(*) FROM user_post", Integer.class);
        Page<UserPost> page = new Page<UserPost>(pageSize, pageNumber, entityCount);
        List<UserPost> list = jdbcOperations.query("SELECT id, title, date, userName, content FROM user_post ORDER BY date DESC LIMIT ?, ? ",
                new JdbcUserPostRepository.UserPostRowMapper(),
                page.getFirstEntityIndex(), page.getPageSize());
        page.setEntities(list);
        return page;
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
