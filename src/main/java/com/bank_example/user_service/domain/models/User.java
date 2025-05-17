package com.bank_example.user_service.domain.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private String id;
    private String name;
    private String lastname;
    private String city;
    private String phone;
    private String email;
    private String identifierType;
    private String identifier;
    private UserType userType;
    private boolean active;


    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
