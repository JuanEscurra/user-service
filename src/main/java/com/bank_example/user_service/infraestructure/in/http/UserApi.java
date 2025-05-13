package com.bank_example.user_service.infraestructure.in.http;

import com.bank_example.user_service.domain.generate.model.CreateUserRequest;
import com.bank_example.user_service.domain.generate.model.UserResponse;
import com.bank_example.user_service.domain.usecases.CreateUserUseCase;
import com.bank_example.user_service.domain.usecases.GetUserByIdUseCase;
import com.bank_example.user_service.infraestructure.in.api.UsersApiDelegate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserApi implements UsersApiDelegate {

    private final GetUserByIdUseCase getUserByIdUseCase;
    private final CreateUserUseCase createUserUseCase;

    @Override
    public Mono<ResponseEntity<UserResponse>> getUserById(String id, ServerWebExchange exchange) {

        return this.getUserByIdUseCase.getUserById(id)
                .map(user -> ResponseEntity.status(HttpStatus.CREATED).body(user));
    }

    @Override
    public Mono<ResponseEntity<UserResponse>> createSavingAccount(Mono<CreateUserRequest> createUserRequest, ServerWebExchange exchange) {
        return createUserRequest
                .flatMap(this.createUserUseCase::createUser)
                .map(user -> ResponseEntity.status(HttpStatus.CREATED).body(user));
    }
}
