package com.hood.data;

import com.hood.pojo.UserPost;

import java.util.List;

/**
 * @author taoranxue on 8/29/16 7:04 PM.
 */
public interface UserPostRepository {
    UserPost save(UserPost userPost);

    List<UserPost> findByUserName(String userName);

    UserPost findById(Long id);
}
