package com.hood.pojo;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

/**
 * @author xueta on 2016/8/10 19:45.
 */
public class HoodUserDetail extends User {
    private final HoodUser hoodUser;

    public HoodUserDetail(HoodUser hoodUser) {
        super(hoodUser.getUsername(), hoodUser.getPassword(), AuthorityUtils.createAuthorityList("RULE_USER"));
        this.hoodUser = hoodUser;
    }

    public HoodUser getHoodUser() {
        return this.hoodUser;
    }
}
