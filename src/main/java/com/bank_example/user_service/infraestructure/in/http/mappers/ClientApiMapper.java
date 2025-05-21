package com.bank_example.user_service.infraestructure.in.http.mappers;

import com.bank_example.user_service.domain.models.Client;
import com.bank_example.user_service.domain.models.entities.Company;
import com.bank_example.user_service.domain.models.entities.Representative;
import com.bank_example.user_service.domain.models.entities.Person;
import com.bank_example.user_service.domain.models.value_objects.IdentifierType;
import com.bank_example.user_service.infraestructure.in.http.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClientApiMapper {

    public ClientResponse toClientResponse(Client client, ZoneId zoneId) {
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setId(client.getId());
        clientResponse.setActive(client.getActive());
        clientResponse.setCreatedAt(client.getCreatedAt().atZone(zoneId).toOffsetDateTime());

        if (client.getClientType() != null) {
            clientResponse.setClientType( ClientType.valueOf(client.getClientType().name()) );
        }

        if (client.getClientCategory() != null) {
            clientResponse.setClientCategory( ClientCategory.valueOf(client.getClientCategory().name()) );
        }

        if (client.getPerson() != null) {
            clientResponse.setPerson( this.toPersonResponse(client.getPerson(), zoneId) );
        } else {
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
        personResponse.setEmail(person.getEmail());
        personResponse.setName(person.getName());
        personResponse.setLastname(person.getLastname());
        personResponse.setPhone(person.getPhone());
        personResponse.setIdentifier(person.getIdentifier());

        if(person.getIdentifierType() != null) {
            personResponse.setIdentifierType(
                    com.bank_example.user_service.infraestructure.in.http.model.IdentifierType.valueOf(person.getIdentifierType().name())
            );
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
        companyResponse.setEmail(company.getEmail());
        companyResponse.setPhone(company.getPhone());
        companyResponse.setIdentifier(company.getIdentifier());
        companyResponse.setFiscalAddress(company.getFiscalAddress());

        companyResponse.setCreatedAt(company.getCreatedAt().atZone(zoneId).toOffsetDateTime());

        return companyResponse;
    }
}
