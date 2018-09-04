package com.foodtruckclub.foodtruck.controllers;

import com.foodtruckclub.foodtruck.models.Home;
import com.foodtruckclub.foodtruck.models.TruckType;
import com.foodtruckclub.foodtruck.models.data.HomeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("home")
public class HomeController {

    @Autowired
    private HomeDao homeDao; //instance of interface

    // Request path: /home
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("trucks", homeDao.findAll()); // it will fetch all the truck data from the DB
        model.addAttribute("title", "Food Truck Club");

        return "home/index";
    }
    @RequestMapping(value= "login")
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/home/login");
        return mav;
        //return "home/login";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddTruckForm(Model model) {
        model.addAttribute("title", "Add Truck");
        model.addAttribute(new Home());
        model.addAttribute("truckTypes", TruckType.values());
        return "home/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddTruckForm(@ModelAttribute  @Valid Home newHome,
                                       Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Truck");
            return "home/add";
        }

        homeDao.save(newHome);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveTruckForm(Model model) {
        model.addAttribute("trucks", homeDao.findAll());
        model.addAttribute("title", "Remove Truck");
        return "home/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveTruckForm(@RequestParam int[] truckIds) {

        for (int truckId : truckIds) {
            homeDao.delete(truckId);
        }

        return "redirect:";
    }

    /*public String displayloc(Model model){
        return "location/index";
    }*/
}
