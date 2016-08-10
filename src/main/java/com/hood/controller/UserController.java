package com.hood.controller;

import com.hood.data.UserRepository;
import com.hood.pojo.HoodUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author xueta on 2016/8/8 12:14.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = "/{username}", method = GET)
    public String showUserProfile(@PathVariable String username, Model model) {
        HoodUser hoodUser = userRepository.findByUsername(username);
        model.addAttribute(hoodUser);
        return "profile";
    }

    @RequestMapping(value = "/login", method = GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute(new HoodUser());
        return "login";
    }

    @RequestMapping(value = "/register", method = POST)
    public String processRegistration(@Valid HoodUser hoodUser, Errors errors, Model model, HttpServletRequest request, HttpServletResponse response) {
        if (errors.hasErrors()) {
            model.addAttribute("regError", true);
            return "login";
        }
        HoodUser _user = userRepository.findByUsername(hoodUser.getUsername());
        if (_user != null && _user.getId() > 0) {
            model.addAttribute("existUsername", true);
            return "login";
        }
        String oPassword = hoodUser.getPassword();
        String ePassword = passwordEncoder.encode(hoodUser.getPassword());
        hoodUser.setPassword(ePassword);
        userRepository.save(hoodUser);
        try {
            request.login(hoodUser.getUsername(), oPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/user/" + hoodUser.getUsername();
    }


}
