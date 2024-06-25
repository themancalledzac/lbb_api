package LittleBlackBookApi.services;

import LittleBlackBookApi.entity.UserEntity;
import LittleBlackBookApi.model.UserModel;
import LittleBlackBookApi.model.createNewContact;
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
    public UserModel getUserByUuid(String uuid) {
        Optional<UserEntity> userEntity = userRepository.findByUuid(UUID.fromString(uuid));
        UserModel userModel = new UserModel();
        userModel.setFirstName(userEntity.get().getFirstName());
        userModel.setLastName(userEntity.get().getLastName());
        userModel.setUuid(String.valueOf(userEntity.get().getUuid()));
        userModel.setContactList(userEntity.get().getContactList().stream()
                .map(contact -> new UserModel(contact.getUuid(), contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber(), contact.getEmail()))
                .collect(Collectors.toList()));
        return userModel;
    }

    @Override
    public UserModel createUser(UserModel user) {
        UserEntity userEntity = userRepository.createUser(user);
        UserModel userModel = new UserModel();
        userModel.setFirstName(userEntity.getFirstName());
        userModel.setLastName(userEntity.getLastName());
        userModel.setUuid(String.valueOf(userEntity.getUuid()));
        userModel.setPhoneNumber(userEntity.getPhoneNumber());
        userModel.setEmail(userEntity.getEmail());
        return userModel;
    }

    @Override
    public UserModel createAndAddContact(createNewContact createNewContact) {

        // create contact User
        UserModel newContactUser = new UserModel();
        newContactUser.setFirstName(createNewContact.getContactFirstName());
        newContactUser.setLastName(createNewContact.getContactLastName());
        newContactUser.setEmail(createNewContact.getContactEmail());
        newContactUser.setPhoneNumber(createNewContact.getContactPhoneNumber());

        // Persist new contact user
        UserEntity newContactEntity = userRepository.createUser(newContactUser);

        // Ensure new contact is fully populated by fetching it from the database
        UserEntity savedContactEntity = userRepository.findById(newContactEntity.getUuid()).orElseThrow(() -> new RuntimeException("New contact not found"));

        // Add Contact to Our User
        Optional<UserEntity> userEntity = userRepository.findById(createNewContact.getUserUuid());
        if (userEntity.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        UserEntity currentUser = userEntity.get();
        currentUser.getContactList().add(savedContactEntity);
        userRepository.save(currentUser);

        // Return updated user
        UserModel returnedCurrentUser = new UserModel();
        returnedCurrentUser.setFirstName(currentUser.getFirstName());
        returnedCurrentUser.setLastName(currentUser.getLastName());
        returnedCurrentUser.setUuid(String.valueOf(currentUser.getUuid()));
        returnedCurrentUser.setPhoneNumber(currentUser.getPhoneNumber());
        returnedCurrentUser.setEmail(currentUser.getEmail());
        returnedCurrentUser.setContactList(currentUser.getContactList().stream()
                .map(contact -> new UserModel(String.valueOf(contact.getUuid()), contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber(), contact.getEmail()))
                .collect(Collectors.toList()));

        return returnedCurrentUser;
    }
}
