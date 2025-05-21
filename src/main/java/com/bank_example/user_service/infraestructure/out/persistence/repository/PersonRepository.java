package com.bank_example.user_service.infraestructure.out.persistence.repository;

import com.bank_example.user_service.infraestructure.out.persistence.models.PersonDoc;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PersonRepository extends ReactiveMongoRepository<PersonDoc, String> {

    Mono<PersonDoc> findByIdentifier(String identifier);
}
