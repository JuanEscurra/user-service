package com.bank_example.user_service.domain.usecases;

import com.bank_example.user_service.domain.models.Client;
import com.bank_example.user_service.domain.models.entities.Company;
import com.bank_example.user_service.domain.models.entities.Person;
import reactor.core.publisher.Mono;

public interface CreateClientUseCase {


    Mono<Client> createCompany(Company createUser);
    Mono<Client> createPerson(Person createUser);
}
