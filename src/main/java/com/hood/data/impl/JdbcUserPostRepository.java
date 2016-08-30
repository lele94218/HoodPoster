package com.hood.data.impl;

import com.hood.data.UserPostRepository;
import com.hood.pojo.UserPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

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
        jdbcOperations.update("INSERT INTO user_post (title, date, userName, content)",
                userPost.getTitle(), userPost.getDate(), userPost.getUserName(), userPost.getContent());
        return userPost;
    }

    public List<UserPost> findByUserName(String userName) {
        // TODO
        return null;
    }


}
