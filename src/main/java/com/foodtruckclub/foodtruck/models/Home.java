package com.foodtruckclub.foodtruck.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Home {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Size(min=3, max=25)
    private String name;

    private TruckType type;

    public Home(String name) {
        this.name = name;
    }

    public Home(){}

    public Integer getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TruckType getType() {
        return type;
    }

    public void setType(TruckType type) {
        this.type = type;
    }

    @OneToOne(mappedBy = "home", cascade = CascadeType.ALL, 
              fetch = FetchType.LAZY, optional = false)
    private Location location;
 
 
    public void setLocation(Location location) {
        if (location == null) {
            if (this.location != null) {
                this.location.setHome(null);
            }
        }
        else {
            location.setHome(this);
        }
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
