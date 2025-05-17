package com.bank_example.user_service.application.ports.out.persistence;

import com.bank_example.user_service.domain.models.User;
import reactor.core.publisher.Mono;

public interface UserFinderPort {

    Mono<User> findById(String id);

}
