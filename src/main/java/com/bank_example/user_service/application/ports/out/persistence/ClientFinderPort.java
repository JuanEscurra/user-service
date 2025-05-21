package com.bank_example.user_service.application.ports.out.persistence;

import com.bank_example.user_service.domain.models.Client;
import reactor.core.publisher.Mono;

public interface ClientFinderPort {

    Mono<Client> findById(String id);

}
