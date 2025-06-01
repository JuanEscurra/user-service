package com.bank_example.user_service.infraestructure.out.persistence.models;

import com.bank_example.user_service.domain.models.value_objects.CompanyCategory;
import com.bank_example.user_service.shared.models.BasicInformation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "companies")
@Data
@EqualsAndHashCode(callSuper = true)
public class CompanyDoc extends BasicInformation {

    private CompanyCategory category;
    private String fiscalAddress;
    private String commercialName;
    private List<RepresentativeDoc> representatives;

}
