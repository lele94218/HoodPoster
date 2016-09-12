package com.hood.controller;

import com.hood.data.UserPostRepository;
import com.hood.pojo.UserPost;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author taoranxue on 8/29/16 1:49 PM.
 */
@Controller
@RequestMapping("/post")
public class PostController {
    private Logger logger = Logger.getLogger(PostController.class);
    private UserPostRepository userPostRepository;

    @Autowired
    public PostController(UserPostRepository userPostRepository) {
        this.userPostRepository = userPostRepository;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUserPost(Model model) {
        model.addAttribute(new UserPost());
        return "post_edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUserPost(@Valid UserPost userPost, Errors errors, Principal principal) {
        logger.info(errors.toString());
        if (errors.hasErrors()) {
            return "post_edit";
        }
        userPost.setDate(new Date());
        userPost.setUserName(principal.getName());
        logger.info(userPost.getContent() + " with in " + userPost.getTitle()
                + " at " + userPost.getDate() + " : " + userPost.getUserName());
        userPostRepository.save(userPost);

        return "redirect: /post/" + userPost.getId().toString();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String findUserPost(@PathVariable Long id, Model model) {
        UserPost userPost = userPostRepository.findById(id);
        Date date = userPost.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy 'at' HH:mm");
        logger.info(userPost.getContent());
        model.addAttribute(userPost);
        model.addAttribute("dateStr", sdf.format(date));
        return "post_detail";
    }

}
