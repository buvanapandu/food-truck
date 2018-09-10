package com.foodtruckclub.foodtruck.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

import com.foodtruckclub.foodtruck.models.Home;
import com.foodtruckclub.foodtruck.models.Location;
import com.foodtruckclub.foodtruck.models.data.HomeDao;
import com.foodtruckclub.foodtruck.models.data.LocationDao;

@Controller
@RequestMapping("location")
public class LocationController {

    @Autowired
    private HomeDao homeDao;

    @Autowired
    private LocationDao locationDao;

    @RequestMapping (value= "add")
    public String index(Model model){
        initModel(model);
        return "location/add";
    }

    private void initModel(Model model) {
        model.addAttribute("title", "Location");
        model.addAttribute(new Location());
        model.addAttribute("trucks", homeDao.findAll()); // it will fetch all the truck data from the DB
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddTruckForm(@ModelAttribute  @Valid Location newLocation,
                                       Errors errors, Model model) {
        System.err.println("Error ! ");
        if (errors.hasErrors()) {
            System.err.println("Error !!! ");

            initModel(model);
            return "location/add";
        }


        Home home = homeDao.findOne(1);
        Location oldLocation = home.getLocation();

        if(oldLocation != null) {
            home.setLocation(null);
            homeDao.save(home);
            locationDao.delete(oldLocation);
        }
        home.setLocation(newLocation);

        homeDao.save(home);
        
        initModel(model);
        return "location/add";
    }    
}
