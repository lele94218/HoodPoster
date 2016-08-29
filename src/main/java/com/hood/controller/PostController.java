package com.hood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author taoranxue on 8/29/16 1:49 PM.
 */
@Controller
@RequestMapping("/post")
public class PostController {
    @RequestMapping("/add")
    public String addUserPost() {
        return "post_edit";
    }
}
