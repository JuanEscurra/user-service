package com.bank_example.user_service.domain.usecases;

import com.bank_example.user_service.domain.models.Client;
import reactor.core.publisher.Mono;

public interface GetClientByIdUseCase {

    Mono<Client> getClientById(String id);
}
