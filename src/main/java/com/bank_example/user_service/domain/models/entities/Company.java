package com.bank_example.user_service.domain.models.entities;

import com.bank_example.user_service.domain.models.value_objects.CompanyCategory;
import com.bank_example.user_service.shared.models.BasicInformation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
public class Company extends BasicInformation {

    private String fiscalAddress;
    private String commercialName;
    private List<Representative> representatives;
    private CompanyCategory category;
}
