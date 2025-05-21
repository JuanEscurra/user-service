package com.bank_example.user_service.application.service;

import com.bank_example.user_service.application.ports.out.persistence.ClientCreatorPort;
import com.bank_example.user_service.domain.models.Client;
import com.bank_example.user_service.shared.models.BasicInformation;
import com.bank_example.user_service.domain.models.entities.Company;
import com.bank_example.user_service.domain.models.entities.Person;
import com.bank_example.user_service.domain.usecases.CreateClientUseCase;
import com.bank_example.user_service.shared.masker.DataMasker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateClientUseCaseImpl implements CreateClientUseCase {

    private final ClientCreatorPort clientCreator;
    private final DataMasker dataMasker;


    @Transactional
    @Override
    public Mono<Client> createCompany(Company company) {
        return this.clientCreator.save(company)
                .doOnSuccess(this::printSuccessfulCreationLog)
                .doOnError(e -> this.printUnSuccessfulCreationLog(e, company));
    }
    @Transactional
    @Override
    public Mono<Client> createPerson(Person person) {
        return this.clientCreator.save(person)
                .doOnSuccess(this::printSuccessfulCreationLog)
                .doOnError(e -> this.printUnSuccessfulCreationLog(e, person));
    }

    private void printSuccessfulCreationLog(Client client) {
        log.info("Client with ID {} created", dataMasker.maskId(client.getIdentifier(), 5));
    }

    private void printUnSuccessfulCreationLog(Throwable e, BasicInformation basicInformation) {
        log.error("Error creating user with ID {}: {}", dataMasker.maskId(basicInformation.getIdentifier(), 5), e.getMessage());
    }
}
