package com.foodtruckclub.foodtruck.models;

public enum TruckType {

    ITALIAN ("Italian"),
    AMERICAN ("American"),
    MEXICAN ("Mexican"),
    INDIAN ("Indian");


    private final String name;

    TruckType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
