package com.bank_example.user_service.infraestructure.out.persistence.repository;

import com.bank_example.user_service.infraestructure.out.persistence.models.ClientDoc;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<ClientDoc, String> {
}
