package LittleBlackBookApi.services;

import LittleBlackBookApi.model.UserModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface UserService {

    @Transactional(readOnly = true)
    UserModel getUserByUsername(String username);

    UserModel createUser(UserModel user);

    void addContact(UUID userUuid, UUID contactUuid);
}
