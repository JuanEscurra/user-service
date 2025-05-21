package com.bank_example.user_service.infraestructure.out.persistence.repository;

import com.bank_example.user_service.infraestructure.out.persistence.models.CompanyDoc;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends ReactiveMongoRepository<CompanyDoc, String> {
}
