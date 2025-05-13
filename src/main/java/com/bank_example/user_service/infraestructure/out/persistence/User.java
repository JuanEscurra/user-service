package com.bank_example.user_service.infraestructure.out.persistence;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
public class User {

    @Id
    private String id;
    private String name;
    private String lastname;
    private String city;
    private String phone;
    private String email;
    private IdentifierType identifierType;
    private String identifier;
    private UserType userType;
    private Boolean active;
}
