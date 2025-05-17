package com.bank_example.user_service.infraestructure.in.http;

import com.bank_example.user_service.domain.generate.model.CreateUserRequest;
import com.bank_example.user_service.domain.generate.model.UserResponse;
import com.bank_example.user_service.domain.usecases.CreateUserUseCase;
import com.bank_example.user_service.domain.usecases.GetUserByIdUseCase;
import com.bank_example.user_service.infraestructure.in.api.UsersApiDelegate;
import com.bank_example.user_service.infraestructure.in.http.mappers.UserApiMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserApiAdapter implements UsersApiDelegate {

    private final UserApiMapper userApiMapper;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final CreateUserUseCase createUserUseCase;

    @Override
    public Mono<ResponseEntity<UserResponse>> getUserById(String id, ServerWebExchange exchange) {

        return this.getUserByIdUseCase.getUserById(id)
                .map(this.userApiMapper::toUserResponse)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<UserResponse>> createSavingAccount(Mono<CreateUserRequest> createUserRequest, ServerWebExchange exchange) {
        return createUserRequest
                .map(this.userApiMapper::toCreateUser)
                .flatMap(this.createUserUseCase::createUser)
                .map(user -> ResponseEntity
                            .status(HttpStatus.CREATED)
                            .body(this.userApiMapper.toUserResponse(user))
                );
    }
}
