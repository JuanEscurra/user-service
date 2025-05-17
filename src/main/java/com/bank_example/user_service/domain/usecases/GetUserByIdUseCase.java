package com.bank_example.user_service.domain.usecases;

import com.bank_example.user_service.domain.models.User;
import reactor.core.publisher.Mono;

public interface GetUserByIdUseCase {

    Mono<User> getUserById(String id);
}
