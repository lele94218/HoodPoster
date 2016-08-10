package com.hood.data;

import com.hood.pojo.HoodUser;

/**
 * @author xueta on 2016/8/8 11:19.
 */
public interface UserRepository {
    HoodUser save(HoodUser hoodUser);

    HoodUser findByUsername(String userName);
}
