package com.bank_example.user_service.infraestructure.out.persistence;

import com.bank_example.user_service.application.ports.out.persistence.ClientFinderPort;
import com.bank_example.user_service.domain.models.Client;
import com.bank_example.user_service.infraestructure.out.persistence.mappers.ClientPersistenceMapper;
import com.bank_example.user_service.infraestructure.out.persistence.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClientFinderAdapter implements ClientFinderPort {

    private final ClientRepository clientRepository;
    private final ClientPersistenceMapper clientPersistenceMapper;

    @Override
    public Mono<Client> findById(String id) {
        return this.clientRepository.findById(id)
                .map(this.clientPersistenceMapper::toClient);
    }
}
