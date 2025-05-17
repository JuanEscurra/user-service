package com.bank_example.user_service.infraestructure.out.persistence.mappers;

import com.bank_example.user_service.domain.models.CreateUser;
import com.bank_example.user_service.domain.models.User;
import com.bank_example.user_service.infraestructure.out.persistence.models.UserDoc;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(UserDoc userDoc) {
        User user = new User();
        user.setId(userDoc.getId());
        user.setActive(userDoc.getActive());
        user.setUserType( userDoc.getUserType() );

        return user;
    }

    public UserDoc toUserDoc(CreateUser createUser) {
        UserDoc userDoc = new UserDoc();
        userDoc.setName(createUser.getName());
        userDoc.setLastname(createUser.getLastname());
        userDoc.setCity(createUser.getCity());
        userDoc.setPhone(createUser.getPhone());
        userDoc.setEmail(createUser.getEmail());
        userDoc.setIdentifier(createUser.getIdentifier());

        userDoc.setIdentifierType( createUser.getIdentifierType() );

        userDoc.setUserType( createUser.getUserType() );

        return userDoc;
    }
}
