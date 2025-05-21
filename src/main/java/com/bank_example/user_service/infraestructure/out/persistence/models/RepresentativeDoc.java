package com.bank_example.user_service.infraestructure.out.persistence.models;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "company_representatives")
@Data
public class RepresentativeDoc {

    private String id;

    @DocumentReference
    private CompanyDoc company;

    @DocumentReference
    private PersonDoc person;
}
