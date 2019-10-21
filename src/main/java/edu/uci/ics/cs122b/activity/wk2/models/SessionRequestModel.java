package edu.uci.ics.cs122b.activity.wk2.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SessionRequestModel {
    @JsonProperty(value = "email", required = true)
    private String email;

    @JsonCreator
    public SessionRequestModel(@JsonProperty(value = "email", required = true)
                                            String email) {
        this.email = email;
    }

    @JsonProperty(value = "email", required = true)
    public String getEmail() {
        return email;
    }
}