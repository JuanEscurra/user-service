package com.bank_example.user_service.infraestructure.out.persistence;

import com.bank_example.user_service.application.ports.out.persistence.UserCreatorPort;
import com.bank_example.user_service.domain.generate.model.CreateUserRequest;
import com.bank_example.user_service.domain.generate.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
public class UserCreatorAdapter implements UserCreatorPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Mono<UserResponse> save(CreateUserRequest request) {
        User user = this.userMapper.convertToCreateUserRequest(request);
        user.setActive(true);

        return this.userRepository.save( user )
                .map(this.userMapper::convertToUserResponse);
    }
}
