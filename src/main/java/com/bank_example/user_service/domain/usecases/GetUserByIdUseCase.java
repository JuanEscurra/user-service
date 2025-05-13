package com.bank_example.user_service.domain.usecases;

import com.bank_example.user_service.domain.generate.model.UserResponse;
import reactor.core.publisher.Mono;

public interface GetUserByIdUseCase {

    Mono<UserResponse> getUserById(String id);
}
