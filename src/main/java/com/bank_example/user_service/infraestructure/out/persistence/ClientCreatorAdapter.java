package com.bank_example.user_service.infraestructure.out.persistence;

import com.bank_example.user_service.application.ports.out.persistence.ClientCreatorPort;
import com.bank_example.user_service.domain.models.Client;
import com.bank_example.user_service.domain.models.entities.Company;
import com.bank_example.user_service.domain.models.entities.Person;
import com.bank_example.user_service.domain.models.entities.Representative;
import com.bank_example.user_service.domain.models.value_objects.ClientType;
import com.bank_example.user_service.infraestructure.out.persistence.mappers.ClientPersistenceMapper;
import com.bank_example.user_service.infraestructure.out.persistence.models.ClientDoc;
import com.bank_example.user_service.infraestructure.out.persistence.models.CompanyDoc;
import com.bank_example.user_service.infraestructure.out.persistence.models.RepresentativeDoc;
import com.bank_example.user_service.infraestructure.out.persistence.models.PersonDoc;
import com.bank_example.user_service.infraestructure.out.persistence.repository.ClientRepository;
import com.bank_example.user_service.infraestructure.out.persistence.repository.CompanyRepository;
import com.bank_example.user_service.infraestructure.out.persistence.repository.PersonRepository;
import com.bank_example.user_service.infraestructure.out.persistence.repository.RepresentativeRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClientCreatorAdapter implements ClientCreatorPort {

    private final ClientRepository clientRepository;
    private final CompanyRepository companyRepository;
    private final PersonRepository personRepository;
    private final RepresentativeRepository representativeRepository;
    private final ClientPersistenceMapper clientPersistenceMapper;


    @Override
    public Mono<Client> save(Company company) {
        CompanyDoc companyDoc = this.clientPersistenceMapper.toCompanyDoc(company);
        companyDoc.setCreatedAt(Instant.now());


        return Mono.zip(
                this.companyRepository.save(companyDoc),
                this.ensurePersonsExist(company.getRepresentatives()).collectList()
        ).flatMap(tuple -> {
            CompanyDoc newCompanyDoc = tuple.getT1();
            List<PersonDoc> personDocs = tuple.getT2();

            ClientDoc clientDoc = this.createClientWithDefaults(ClientType.COMPANY);
            clientDoc.setCompanyId(new ObjectId(newCompanyDoc.getId()));

            List<RepresentativeDoc> reps = personDocs.stream().map(personDoc -> {
                RepresentativeDoc rep = new RepresentativeDoc();
                rep.setCompany(newCompanyDoc);
                rep.setPerson(personDoc);
                return rep;
            }).collect(Collectors.toList());

            return Mono.zip(
                    this.clientRepository.save(clientDoc),
                    this.representativeRepository.saveAll(reps).collectList()
            ).map(result -> this.clientPersistenceMapper.toClient(result.getT1()));
        });
    }

    @Override
    public Mono<Client> save(Person person) {
        PersonDoc personDoc = this.clientPersistenceMapper.toPersonDoc(person);
        personDoc.setActive(true);
        personDoc.setCreatedAt(Instant.now());

        return this.personRepository.save(personDoc)
                .map(newPersonDoc -> {
                    ClientDoc clientDoc = this.createClientWithDefaults(ClientType.PERSONAL);
                    clientDoc.setPersonId(new ObjectId(newPersonDoc.getId()));
                    return clientDoc;
                })
                .flatMap(this.clientRepository::save)
                .map(this.clientPersistenceMapper::toClient);
    }

    private ClientDoc createClientWithDefaults(ClientType clientType) {
        ClientDoc clientDoc = new ClientDoc();
        clientDoc.setClientType(clientType);
        clientDoc.setActive(true);
        clientDoc.setCreatedAt(Instant.now());

        return clientDoc;
    }


    private Flux<PersonDoc> ensurePersonsExist(List<Representative> representatives) {
        return Flux.fromIterable(representatives)
                .concatMap(rep -> {
                    String identifier = rep.getPerson().getIdentifier();

                    return this.personRepository.findByIdentifier(identifier)
                            .switchIfEmpty(
                                    this.personRepository.save(this.clientPersistenceMapper.toPersonDoc(rep.getPerson()))
                            );
                });
    }

}
