package com.workchat.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String uuid;
    @Column(unique = true)
    private String username;
    private String fullName;
    private String password;
    @Column(unique = true)
    private String email;
    private String createdON;

}
