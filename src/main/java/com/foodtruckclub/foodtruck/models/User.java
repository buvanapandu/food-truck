package com.foodtruckclub.foodtruck.models;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id // primary key
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=5, max=12)
    private String username;

    //optional email
    @Email
    private String email;

    @NotNull
    @Size(min=6, message = "password should be minimum 6")
    private String password;
    //add error message


    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @ManyToOne
    private Home home;

    public User() {
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
