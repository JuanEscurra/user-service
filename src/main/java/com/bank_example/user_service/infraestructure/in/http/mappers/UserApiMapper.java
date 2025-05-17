package com.bank_example.user_service.infraestructure.in.http.mappers;

import com.bank_example.user_service.domain.generate.model.CreateUserRequest;
import com.bank_example.user_service.domain.generate.model.UserResponse;
import com.bank_example.user_service.domain.models.CreateUser;
import com.bank_example.user_service.domain.models.IdentifierType;
import com.bank_example.user_service.domain.models.User;
import com.bank_example.user_service.domain.models.UserType;
import org.springframework.stereotype.Component;

@Component
public class UserApiMapper {


    public UserResponse toUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setActive(user.isActive());

        if (user.getUserType() != null) {
            userResponse.setUserType(UserResponse.UserTypeEnum.valueOf(user.getUserType().name()));
        }

        return userResponse;
    }

    public CreateUser toCreateUser(CreateUserRequest request) {
        CreateUser createUser = new CreateUser();
        createUser.setName(request.getName());
        createUser.setLastname(request.getLastname());
        createUser.setCity(request.getCity());
        createUser.setPhone(request.getPhone());
        createUser.setEmail(request.getEmail());
        createUser.setIdentifier(request.getIdentifier());

        if (request.getIdentifierType() != null) {
            createUser.setIdentifierType(IdentifierType.valueOf(request.getIdentifierType().name()));
        }

        if (request.getUserType() != null) {
            createUser.setUserType(UserType.valueOf(request.getUserType().name()));
        }

        return createUser;
    }
}
