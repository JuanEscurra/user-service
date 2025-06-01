package com.bank_example.user_service.infraestructure.out.persistence;

import com.bank_example.user_service.application.ports.out.persistence.ClientFinderPort;
import com.bank_example.user_service.domain.models.Client;
import com.bank_example.user_service.infraestructure.out.persistence.mappers.ClientPersistenceMapper;
import com.bank_example.user_service.infraestructure.out.persistence.models.CompanyDoc;
import com.bank_example.user_service.infraestructure.out.persistence.models.PersonDoc;
import com.bank_example.user_service.infraestructure.out.persistence.repository.ClientRepository;
import com.bank_example.user_service.infraestructure.out.persistence.repository.CompanyRepository;
import com.bank_example.user_service.infraestructure.out.persistence.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClientFinderAdapter implements ClientFinderPort {

    private final ClientRepository clientRepository;
    private final PersonRepository personRepository;
    private final CompanyRepository companyRepository;
    private final ClientPersistenceMapper clientPersistenceMapper;

    @Override
    public Mono<Client> findById(String id) {
        return this.clientRepository.findById(id)
                .flatMap(clientDoc -> {

                    if ( clientDoc.getPersonId() != null) {
                        return Mono.zip(
                                Mono.just(clientDoc),
                                personRepository.findById(clientDoc.getPersonId().toHexString())
                        )
                                .map(tuple -> {
                                    Client client = clientPersistenceMapper.toClient(tuple.getT1());
                                    client.setPerson( clientPersistenceMapper.toPerson(tuple.getT2()) );
                                    return client;
                                });
                    }

                    if ( clientDoc.getCompanyId() != null) {
                        return Mono.zip(
                                        Mono.just(clientDoc),
                                        companyRepository.findById(clientDoc.getCompanyId().toHexString())
                                )
                                .map(tuple -> {
                                    Client client = clientPersistenceMapper.toClient(tuple.getT1());
                                    client.setCompany( clientPersistenceMapper.toCompany(tuple.getT2()) );
                                    return client;
                                });
                    }

                    return Mono.just(this.clientPersistenceMapper.toClient(clientDoc));
        });
    }
}
