package LittleBlackBookApi.services;

import LittleBlackBookApi.model.CreateUserModel;
import LittleBlackBookApi.model.UserModel;
import LittleBlackBookApi.model.createNewContact;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    @Transactional(readOnly = true)
    UserModel getUserByUuid(String uuid);

    UserModel createUser(CreateUserModel user);

//    void addContact(UUID userUuid, UUID contactUuid);

    UserModel createAndAddContact(createNewContact createNewContact);
}
