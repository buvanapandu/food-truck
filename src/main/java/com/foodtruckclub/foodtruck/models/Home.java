package com.foodtruckclub.foodtruck.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Home {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=25)
    private String name;

    private TruckType type;

    public Home(String name) {
        this.name = name;
    }

    public Home(){}

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
}
