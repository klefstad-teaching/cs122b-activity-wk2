package edu.uci.ics.cs122b.activity2.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentRequestModel {

    @JsonProperty(value = "firstName")
    private String firstName;
    @JsonProperty(value = "lastName")
    private String lastName;
    @JsonProperty(value = "gpa")
    private Float gpa;
    @JsonProperty(value = "major")
    private String major;
    @JsonProperty(value = "email")
    private String email;

    public StudentRequestModel(@JsonProperty(value = "firstName") String firstName,
                               @JsonProperty(value = "lastName") String lastName,
                               @JsonProperty(value = "email") String email,
                               @JsonProperty(value = "gpa") Float gpa,
                               @JsonProperty(value = "major") String major) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gpa = gpa;
        this.major = major;
        this.email = email;
    }

    @JsonProperty(value = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty(value = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty(value = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty(value = "gpa")
    public Float getGpa() {
        return gpa;
    }

    public void setGpa(Float gpa) {
        this.gpa = gpa;
    }

    @JsonProperty(value = "major")
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}