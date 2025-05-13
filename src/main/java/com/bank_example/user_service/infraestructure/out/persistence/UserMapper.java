package com.bank_example.user_service.infraestructure.out.persistence;

import com.bank_example.user_service.domain.generate.model.CreateUserRequest;
import com.bank_example.user_service.domain.generate.model.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse convertToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setActive(user.getActive());

        if(user.getUserType() != null) {
            userResponse.setUserType( UserResponse.UserTypeEnum.valueOf(user.getUserType().name()));
        }

        return userResponse;
    }

    public User convertToCreateUserRequest(CreateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setLastname(request.getLastname());
        user.setCity(request.getCity());
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());
        user.setIdentifier(request.getIdentifier());

        if(request.getIdentifierType() != null) {
            user.setIdentifierType(IdentifierType.valueOf(request.getIdentifierType().name()));
        }

        if(request.getUserType() != null) {
            user.setUserType(UserType.valueOf(request.getUserType().name()));
        }

        return user;
    }
}
