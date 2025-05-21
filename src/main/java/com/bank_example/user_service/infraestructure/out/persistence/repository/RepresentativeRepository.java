package com.bank_example.user_service.infraestructure.out.persistence.repository;

import com.bank_example.user_service.infraestructure.out.persistence.models.RepresentativeDoc;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RepresentativeRepository extends ReactiveMongoRepository<RepresentativeDoc, String> {
}
