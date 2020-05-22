package com.webiotsolutions.school.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class AuthenticationResponse implements Serializable {
    private String token;
    private String role;
    private String username;
}
