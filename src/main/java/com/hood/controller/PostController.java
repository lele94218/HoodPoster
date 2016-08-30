package com.hood.controller;

import com.hood.pojo.UserPost;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author taoranxue on 8/29/16 1:49 PM.
 */
@Controller
@RequestMapping("/post")
public class PostController {
    private Logger logger = Logger.getLogger(PostController.class);
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUserPost(Model model) {
        model.addAttribute(new UserPost());
        return "post_edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUserPost(@Valid UserPost userPost, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "post_edit";
        }
        logger.info(userPost.getContent());
        return "home";
    }
}
