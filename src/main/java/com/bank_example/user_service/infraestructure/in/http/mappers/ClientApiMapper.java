package com.bank_example.user_service.infraestructure.in.http.mappers;

import com.bank_example.user_service.domain.models.Client;
import com.bank_example.user_service.domain.models.entities.Company;
import com.bank_example.user_service.domain.models.entities.Representative;
import com.bank_example.user_service.domain.models.entities.Person;
import com.bank_example.user_service.domain.models.value_objects.CompanyCategory;
import com.bank_example.user_service.domain.models.value_objects.IdentifierType;
import com.bank_example.user_service.domain.models.value_objects.PersonCategory;
import com.bank_example.user_service.infraestructure.in.http.model.*;
import com.bank_example.user_service.shared.masker.DataMasker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClientApiMapper {

    private final DataMasker dataMasker;

    public ClientResponse toClientResponse(Client client, ZoneId zoneId) {
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setId(client.getId());
        clientResponse.setActive(client.getActive());
        clientResponse.setCreatedAt(client.getCreatedAt().atZone(zoneId).toOffsetDateTime());

        if (client.getClientType() != null) {
            clientResponse.setClientType( ClientType.valueOf(client.getClientType().name()) );
        }

        if (client.getPerson() != null) {
            clientResponse.setPerson( this.toPersonResponse(client.getPerson(), zoneId) );
        }
        if (client.getCompany() != null) {
            clientResponse.setCompany( this.toCompanyResponse(client.getCompany(), zoneId) );
        }

        return clientResponse;
    }

    public Person toPerson(CreatePersonRequest request) {
        Person createPerson = new Person();
        createPerson.setName(request.getName());
        createPerson.setLastname(request.getLastname());
        createPerson.setEmail(request.getEmail());
        createPerson.setPhone(request.getPhone());
        createPerson.setIdentifier(request.getIdentifier());

        if(request.getIdentifierType() != null) {
            createPerson.setIdentifierType( IdentifierType.valueOf(request.getIdentifierType().name()) );
        }

        if (request.getCategory() != null) {
            createPerson.setCategory( PersonCategory.valueOf(request.getCategory().name()) );
        }

        return createPerson;
    }

    public Company toCompany(CreateCompanyRequest request) {
        Company company = new Company();
        company.setName(request.getName());
        company.setEmail(request.getEmail());
        company.setPhone(request.getPhone());
        company.setIdentifier(request.getIdentifier());
        company.setFiscalAddress(request.getFiscalAddress());
        company.setCommercialName(request.getCommercialName());

        if (request.getCategory() != null) {
            company.setCategory( CompanyCategory.valueOf(request.getCategory().name()) );
        }

        List<Representative> representatives = request.getRepresentatives().stream()
                .map(representative -> {
                    Person person = this.toPerson(representative);
                    return new Representative(company, person);
                }).collect(Collectors.toList());
        company.setRepresentatives(representatives);

        return company;
    }

    public PersonResponse toPersonResponse(Person person, ZoneId zoneId) {
        PersonResponse personResponse = new PersonResponse();
        personResponse.setId(person.getId());
        personResponse.setEmail( dataMasker.maskEmail(person.getEmail()) );
        personResponse.setName(person.getName());
        personResponse.setLastname(person.getLastname());
        personResponse.setPhone( dataMasker.maskPhoneNumber(person.getPhone()) );
        personResponse.setIdentifier( dataMasker.maskId(person.getIdentifier(), 5));

        if(person.getIdentifierType() != null) {
            personResponse.setIdentifierType(
                    com.bank_example.user_service.infraestructure.in.http.model.IdentifierType.valueOf(person.getIdentifierType().name())
            );
        }

        if (person.getCategory() != null) {
            personResponse.setCategory( PersonResponse.CategoryEnum.valueOf(person.getCategory().name()) );
        }

        if (person.getCreatedAt() != null) {
            personResponse.setCreatedAt(person.getCreatedAt().atZone(zoneId).toOffsetDateTime());
        }

        return personResponse;
    }

    public CompanyResponse toCompanyResponse(Company company, ZoneId zoneId) {
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setId(company.getId());
        companyResponse.setCommercialName(company.getCommercialName());
        companyResponse.setName(company.getName());
        companyResponse.setEmail( dataMasker.maskEmail(company.getEmail()) );
        companyResponse.setPhone( dataMasker.maskPhoneNumber(company.getPhone()) );
        companyResponse.setIdentifier( dataMasker.maskId(company.getIdentifier(), 5) );
        companyResponse.setFiscalAddress(company.getFiscalAddress());

        companyResponse.setCreatedAt(company.getCreatedAt().atZone(zoneId).toOffsetDateTime());

        if ( company.getCategory() != null) {
            companyResponse.setCategory( CompanyResponse.CategoryEnum.valueOf(company.getCategory().name()) );
        }

        return companyResponse;
    }
}
