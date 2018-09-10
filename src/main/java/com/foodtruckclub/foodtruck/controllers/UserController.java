package com.foodtruckclub.foodtruck.controllers;

import com.foodtruckclub.foodtruck.models.User;
import com.foodtruckclub.foodtruck.models.data.UserDao;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping (value ="login", method=RequestMethod.GET)
    public String loginInputForm(Model model){
        model.addAttribute("title", "User-Login");
        model.addAttribute(new User());
        model.addAttribute("user", "Please enter login details");
        return "/user/login";
    }

    @RequestMapping (value ="login", method=RequestMethod.POST)
    public String loginDisplayForm(Model model,@ModelAttribute @Valid User user, Errors errors) {

        model.addAttribute("title", "Welcome to FoodTruck");
        List<User> existUser = userDao.findByUsername(user.getUsername());
        User exiUser = null;
        if (!existUser.isEmpty()) {
            exiUser = existUser.get(0);
        }
        if (exiUser!=null) {
            if (user.getUsername().equals(exiUser.getUsername()) && user.getPassword().equals(exiUser.getPassword())) {
                model.addAttribute("message", "Successful loggin");
                return "/user/index";
            }else{
                model.addAttribute("message", "Invalid credentials");
                return "/user/login";
            }
                //return "/user/index";
        } else {
                model.addAttribute("message", "User does not exist");
                return "/user/add";
            }

        }

    @RequestMapping (value ="logout")
    public String logout(){
        return "/user/logout";
    }
}
