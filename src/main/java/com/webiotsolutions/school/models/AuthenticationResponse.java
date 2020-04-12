package com.webiotsolutions.school.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class AuthenticationResponse implements Serializable {
    private String token;

    public AuthenticationResponse(String jwt) {
        this.token = jwt;
    }
}
