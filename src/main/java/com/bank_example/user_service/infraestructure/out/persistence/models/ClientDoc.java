package com.bank_example.user_service.infraestructure.out.persistence.models;

import com.bank_example.user_service.domain.models.value_objects.ClientCategory;
import com.bank_example.user_service.domain.models.value_objects.ClientType;
import com.bank_example.user_service.shared.models.AuditTrail;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "clients")
@Data
@EqualsAndHashCode(callSuper = false)
public class ClientDoc extends AuditTrail {

    private String id;

    @DocumentReference
    private PersonDoc person;

    @DocumentReference
    private CompanyDoc company;
    private ClientType clientType;
    private ClientCategory clientCategory;
    private Boolean active;
}