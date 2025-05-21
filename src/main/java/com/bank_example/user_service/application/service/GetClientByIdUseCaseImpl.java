package com.bank_example.user_service.application.service;

import com.bank_example.user_service.application.ports.out.persistence.ClientFinderPort;
import com.bank_example.user_service.domain.models.Client;
import com.bank_example.user_service.domain.usecases.GetClientByIdUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetClientByIdUseCaseImpl implements GetClientByIdUseCase {

    private final ClientFinderPort clientFinder;

    @Override
    public Mono<Client> getClientById(String id) {
        return this.clientFinder.findById(id)
                .doOnSuccess(this::printDataRetriavalLog)
                .doOnError(e -> printClientNotFoundLog(e, id));
    }


    private void printDataRetriavalLog(Client client) {
        log.info("Client found by id: {}", client.getId());
    }

    private void printClientNotFoundLog(Throwable e, String id) {
        log.error("Client Not found by id {}: {}", id,e.getMessage());
    }
}
