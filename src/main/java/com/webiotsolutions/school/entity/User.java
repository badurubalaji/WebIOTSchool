package com.webiotsolutions.school.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "User")
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private Boolean active;
    private String role;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialNonExpired;
    private Boolean enabled;

}
