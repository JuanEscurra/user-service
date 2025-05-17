package com.bank_example.user_service.application.service;

import com.bank_example.user_service.application.ports.out.persistence.UserCreatorPort;
import com.bank_example.user_service.domain.models.CreateUser;
import com.bank_example.user_service.domain.models.User;
import com.bank_example.user_service.domain.usecases.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserCreatorPort userCreator;


    @Override
    public Mono<User> createUser(CreateUser createUser) {

        return this.userCreator.save(createUser)
                .doOnSuccess(user -> log.info("User created: {}", user.getId()))
                .doOnError(e -> log.error("Error creating user: {}", e.getMessage()));
    }
}
