package com.bank_example.user_service.domain.models;

import lombok.Data;

@Data
public class CreateUser {

    private String name;
    private String lastname;
    private String city;
    private String phone;
    private String email;
    private IdentifierType identifierType;
    private String identifier;
    private UserType userType;
}
