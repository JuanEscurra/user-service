package com.bank_example.user_service.infraestructure.out.persistence;

import com.bank_example.user_service.application.ports.out.persistence.UserFinderPort;
import com.bank_example.user_service.domain.generate.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserFinderAdapter implements UserFinderPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Mono<UserResponse> findById(String id) {
        return this.userRepository.findById(id)
                .map(this.userMapper::convertToUserResponse);
    }
}
