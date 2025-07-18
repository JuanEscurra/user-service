package com.bank_example.user_service.infraestructure.out.persistence.mappers;

import com.bank_example.user_service.domain.models.Client;
import com.bank_example.user_service.domain.models.entities.Company;
import com.bank_example.user_service.domain.models.entities.Person;
import com.bank_example.user_service.infraestructure.out.persistence.models.ClientDoc;
import com.bank_example.user_service.infraestructure.out.persistence.models.CompanyDoc;
import com.bank_example.user_service.infraestructure.out.persistence.models.PersonDoc;
import org.springframework.stereotype.Component;

@Component
public class ClientPersistenceMapper {

    public CompanyDoc toCompanyDoc(Company company) {
        CompanyDoc companyDoc = new CompanyDoc();
        companyDoc.setId(company.getId());
        companyDoc.setName(company.getName());
        companyDoc.setIdentifier(company.getIdentifier());
        companyDoc.setEmail(company.getEmail());
        companyDoc.setPhone(company.getPhone());
        companyDoc.setFiscalAddress(company.getFiscalAddress());
        companyDoc.setCommercialName(company.getCommercialName());
        companyDoc.setCategory(company.getCategory());
        return companyDoc;
    }

    public PersonDoc toPersonDoc(Person person) {

        PersonDoc personDoc = new PersonDoc();
        personDoc.setId(person.getId());
        personDoc.setName(person.getName());
        personDoc.setLastname(person.getLastname());
        personDoc.setIdentifier(person.getIdentifier());
        personDoc.setEmail(person.getEmail());
        personDoc.setPhone(person.getPhone());
        personDoc.setCategory(person.getCategory());

        if(person.getIdentifierType() != null) {
            personDoc.setIdentifierType(person.getIdentifierType());
        }

        return personDoc;
    }

    public Client toClient(ClientDoc clientDoc) {
        if (clientDoc == null) return null;

        Client client = new Client();
        client.setId(clientDoc.getId());
        client.setClientType(clientDoc.getClientType());
        client.setActive(clientDoc.getActive());
        client.setCreatedAt(clientDoc.getCreatedAt());

        return client;
    }

    public Person toPerson(PersonDoc personDoc) {
        if (personDoc == null) return null;

        Person person = new Person();
        person.setId(personDoc.getId());
        person.setName(personDoc.getName());
        person.setLastname(personDoc.getLastname());
        person.setIdentifier(personDoc.getIdentifier());
        person.setEmail(personDoc.getEmail());
        person.setPhone(personDoc.getPhone());
        person.setActive(personDoc.getActive());
        person.setIdentifierType(personDoc.getIdentifierType());
        person.setCreatedAt(personDoc.getCreatedAt());
        person.setCategory(personDoc.getCategory());
        return person;
    }

    public Company toCompany(CompanyDoc companyDoc) {
        if (companyDoc == null) return null;

        Company company = new Company();
        company.setId(companyDoc.getId());
        company.setName(companyDoc.getName());
        company.setIdentifier(companyDoc.getIdentifier());
        company.setEmail(companyDoc.getEmail());
        company.setPhone(companyDoc.getPhone());
        company.setFiscalAddress(companyDoc.getFiscalAddress());
        company.setCommercialName(companyDoc.getCommercialName());
        company.setCreatedAt(companyDoc.getCreatedAt());
        company.setCategory(companyDoc.getCategory());
        return company;
    }
}
