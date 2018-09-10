package com.foodtruckclub.foodtruck.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Location {

    @Transient
    private Integer truckId;
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "home_id")
    private Home home;

    @NotNull
    @Size(min=3, max=25)
    private String streetAddress;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String zip;

    public Location() {
    }

    public void setHome(Home home) {
     this.home = home;
    } 

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public Integer getTruckId() {
        return truckId;
    }
    /**
     * @return the streetAddress
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * @param streetAddress the streetAddress to set
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }
}
