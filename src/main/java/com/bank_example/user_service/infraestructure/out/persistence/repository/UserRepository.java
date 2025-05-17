package com.bank_example.user_service.infraestructure.out.persistence.repository;

import com.bank_example.user_service.infraestructure.out.persistence.models.UserDoc;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<UserDoc, String> {
}
