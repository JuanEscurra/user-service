package com.bank_example.user_service.infraestructure.out.persistence.models;


import com.bank_example.user_service.domain.models.value_objects.IdentifierType;
import com.bank_example.user_service.shared.models.BasicInformation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "persons")
@Data
@EqualsAndHashCode(callSuper = true)
public class PersonDoc extends BasicInformation {

    private String lastname;
    private IdentifierType identifierType;

}
