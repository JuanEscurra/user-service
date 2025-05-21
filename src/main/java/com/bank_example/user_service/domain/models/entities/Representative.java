package com.bank_example.user_service.domain.models.entities;

import com.bank_example.user_service.shared.models.AuditTrail;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Representative extends AuditTrail {

    private String id;
    private Company company;
    private Person person;


    public Representative(Company company, Person person) {
        this.person = person;
        this.company = company;
    }
}
