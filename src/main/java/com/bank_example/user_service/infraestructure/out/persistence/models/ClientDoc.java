package com.bank_example.user_service.infraestructure.out.persistence.models;

import com.bank_example.user_service.domain.models.value_objects.ClientType;
import com.bank_example.user_service.shared.models.AuditTrail;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clients")
@Data
@EqualsAndHashCode(callSuper = false)
public class ClientDoc extends AuditTrail {

    private String id;
    private ObjectId personId;
    private ObjectId companyId;
    private ClientType clientType;
    private Boolean active;
}