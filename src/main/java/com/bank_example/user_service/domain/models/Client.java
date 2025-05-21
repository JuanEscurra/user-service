package com.bank_example.user_service.domain.models;

import com.bank_example.user_service.domain.models.entities.Company;
import com.bank_example.user_service.domain.models.entities.Person;
import com.bank_example.user_service.domain.models.value_objects.ClientCategory;
import com.bank_example.user_service.domain.models.value_objects.ClientType;
import com.bank_example.user_service.shared.models.AuditTrail;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class Client extends AuditTrail {

    private String id;
    private Person person;
    private Company company;
    private ClientType clientType;
    private ClientCategory clientCategory;
    private Boolean active;


    public String getIdentifier() {
        if (person != null) {
            return person.getIdentifier();
        } else if (company != null) {
            return company.getIdentifier();
        }
        return null;
    }
}
