package com.bank_example.user_service.application.ports.out.persistence;

import com.bank_example.user_service.domain.models.Client;
import com.bank_example.user_service.domain.models.entities.Company;
import com.bank_example.user_service.domain.models.entities.Person;
import reactor.core.publisher.Mono;

public interface ClientCreatorPort {

    Mono<Client> save(Company company);
    Mono<Client> save(Person person);
}
