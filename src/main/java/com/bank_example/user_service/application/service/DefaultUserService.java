package com.bank_example.user_service.application.service;

import com.bank_example.user_service.application.ports.out.persistence.UserCreatorPort;
import com.bank_example.user_service.application.ports.out.persistence.UserFinderPort;
import com.bank_example.user_service.domain.generate.model.CreateUserRequest;
import com.bank_example.user_service.domain.generate.model.UserResponse;
import com.bank_example.user_service.domain.usecases.CreateUserUseCase;
import com.bank_example.user_service.domain.usecases.GetUserByIdUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class DefaultUserService implements
        CreateUserUseCase,
        GetUserByIdUseCase {

    private final UserCreatorPort userCreator;
    private final UserFinderPort userfinder;

    @Override
    public Mono<UserResponse> createUser(CreateUserRequest request) {

        return this.userCreator.save(request)
                .doOnSuccess(user -> log.info("User created: {}", user.getId()))
                .doOnError(e -> log.error("Error creating user: {}", e.getMessage()));
    }

    @Override
    public Mono<UserResponse> getUserById(String id) {

        return this.userfinder.findById(id)
                .doOnSuccess(user -> log.info("User found by id: {}", user.getId()))
                .doOnError(e -> log.error("User Not found by id: {}", e.getMessage()));
    }
}
