package com.bank_example.user_service.application.ports.out.persistence;

import com.bank_example.user_service.domain.generate.model.CreateUserRequest;
import com.bank_example.user_service.domain.generate.model.UserResponse;
import reactor.core.publisher.Mono;

public interface UserCreatorPort {

    Mono<UserResponse> save(CreateUserRequest request);
}
