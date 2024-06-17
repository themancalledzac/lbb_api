package LittleBlackBookApi.services;

import LittleBlackBookApi.entity.UserEntity;
import LittleBlackBookApi.model.UserModel;
import LittleBlackBookApi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserModel getUserByUsername(String username) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        UserModel userModel = new UserModel();
        userModel.setName(userEntity.get().getName());
        userModel.setUsername(userEntity.get().getUsername());
        userModel.setUuid(userEntity.get().getUuid());
        userModel.setContactList(userEntity.get().getContactList().stream()
                .map(contact -> new UserModel(contact.getUuid(), contact.getName(), contact.getUsername(), null))
                .collect(Collectors.toList()));
        return userModel;
    }

    @Override
    public UserModel createUser(UserModel user) {
        Optional<UserEntity> userEntity = userRepository.createUser(user);
        UserModel userModel = new UserModel();
        userModel.setName(userEntity.get().getName());
        userModel.setUsername(userEntity.get().getUsername());
        userModel.setUuid(userEntity.get().getUuid());
        return userModel;
    }

    @Override
    public void addContact(UUID userUuid, UUID contactUuid) {
        Optional<UserEntity> userEntity = userRepository.findById(userUuid);
        Optional<UserEntity> contactEntity = userRepository.findById(contactUuid);

        if (userEntity.isPresent() && contactEntity.isPresent()) {
            UserEntity user = userEntity.get();
            UserEntity contact = contactEntity.get();
            user.getContactList().add(contact);
            userRepository.save(user);
        } else {
            throw new RuntimeException("User or Contact not found");
        }
    }
}
