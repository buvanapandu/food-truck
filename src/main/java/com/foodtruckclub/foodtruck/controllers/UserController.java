package com.foodtruckclub.foodtruck.controllers;

import com.foodtruckclub.foodtruck.models.User;
import com.foodtruckclub.foodtruck.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping (value= "add")
    public String index(Model model){
        model.addAttribute("title", "User-Signup");
        model.addAttribute("user", new User());
        return "user/add";
    }

    @RequestMapping (value ="add",  method=RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid User user, String verify, Errors errors){
        List<User> sameUser = userDao.findByUsername(user.getUsername());
        if(!errors.hasErrors() && user.getPassword().equals(verify) && sameUser.isEmpty()) {
            model.addAttribute("user", user);
            userDao.save(user);
            return "user/index";
        }
        else{
            model.addAttribute("user", user);
            model.addAttribute("title", "User-Signup");
            if(!user.getPassword().equals(verify)){
                model.addAttribute("message", "Password must match");
                user.setPassword("");
            }
            if(!sameUser.isEmpty()){
                model.addAttribute("message", "Username already taken use different one");
            }
            return "/user/add";
        }
    }

}
