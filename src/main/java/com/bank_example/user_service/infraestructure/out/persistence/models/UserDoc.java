package com.bank_example.user_service.infraestructure.out.persistence.models;


import com.bank_example.user_service.domain.models.IdentifierType;
import com.bank_example.user_service.domain.models.UserType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
public class UserDoc {

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
