package LittleBlackBookApi.services;

import LittleBlackBookApi.model.UserModel;
import LittleBlackBookApi.model.createNewContact;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface UserService {

    @Transactional(readOnly = true)
    UserModel getUserByUuid(String uuid);

    UserModel createUser(UserModel user);

//    void addContact(UUID userUuid, UUID contactUuid);

    UserModel createAndAddContact(createNewContact createNewContact);
}
