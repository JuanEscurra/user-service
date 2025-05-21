package com.bank_example.user_service.domain.models.entities;


import com.bank_example.user_service.domain.models.value_objects.IdentifierType;
import com.bank_example.user_service.shared.models.BasicInformation;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class Person extends BasicInformation {

    private String lastname;
    private IdentifierType identifierType;

}
