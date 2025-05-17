package com.bank_example.user_service.infraestructure.out.persistence;

import com.bank_example.user_service.application.ports.out.persistence.UserCreatorPort;
import com.bank_example.user_service.domain.models.CreateUser;
import com.bank_example.user_service.domain.models.User;
import com.bank_example.user_service.infraestructure.out.persistence.mappers.UserMapper;
import com.bank_example.user_service.infraestructure.out.persistence.models.UserDoc;
import com.bank_example.user_service.infraestructure.out.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserCreatorAdapter implements UserCreatorPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Mono<User> save(CreateUser createUser) {
        UserDoc userDoc = this.userMapper.toUserDoc(createUser);
        userDoc.setActive(true);

        return this.userRepository.save(userDoc)
                .map(this.userMapper::toUser);
    }
}
