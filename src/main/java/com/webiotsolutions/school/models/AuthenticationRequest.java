package com.webiotsolutions.school.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class AuthenticationRequest implements Serializable {
    private String username;
    private String password;

}
