package com.bank_example.user_service.application.service;

import com.bank_example.user_service.application.ports.out.persistence.UserFinderPort;
import com.bank_example.user_service.domain.models.User;
import com.bank_example.user_service.domain.usecases.GetUserByIdUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetUserByIdUseCaseImpl implements GetUserByIdUseCase {

    private final UserFinderPort userfinder;


    @Override
    public Mono<User> getUserById(String id) {

        return this.userfinder.findById(id)
                .doOnSuccess(user -> log.info("User found by id: {}", user.getId()))
                .doOnError(e -> log.error("User Not found by id: {}", e.getMessage()));
    }
}
