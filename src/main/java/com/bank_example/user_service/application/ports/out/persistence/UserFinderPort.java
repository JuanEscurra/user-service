package com.bank_example.user_service.application.ports.out.persistence;

import com.bank_example.user_service.domain.generate.model.UserResponse;
import reactor.core.publisher.Mono;

public interface UserFinderPort {

    Mono<UserResponse> findById(String id);

}
