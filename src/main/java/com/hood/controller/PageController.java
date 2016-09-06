package com.hood.controller;

import com.hood.common.Page;
import com.hood.data.UserPostRepository;
import com.hood.pojo.UserPost;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author taoranxue on 9/6/16 12:29 AM.
 */
@Controller
public class PageController {
    private Logger logger = Logger.getLogger(PageController.class);
    private UserPostRepository userPostRepository;

    @Autowired
    public PageController(UserPostRepository userPostRepository) {
        this.userPostRepository = userPostRepository;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(@RequestParam(value = "pn", defaultValue = "0")Integer pageNumber,
                           @RequestParam(value = "ps", defaultValue = "10")Integer pageSize,
                           Model model) {
        Page<UserPost> page = userPostRepository.findAllPage(pageNumber, pageSize);
        model.addAttribute(page);
        return "new_home";
    }
}
