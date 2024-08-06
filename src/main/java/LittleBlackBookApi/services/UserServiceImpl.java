package LittleBlackBookApi.services;

import LittleBlackBookApi.entity.UserEntity;
import LittleBlackBookApi.model.CreateUserModel;
import LittleBlackBookApi.model.UserModel;
import LittleBlackBookApi.model.createNewContact;
import LittleBlackBookApi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel mapToUserModel(UserEntity user) {
        UserModel userModel = new UserModel();
        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setUuid(String.valueOf(user.getUuid()));
        userModel.setPhoneNumber(user.getPhoneNumber());
        userModel.setEmail(user.getEmail());
        if (user.getContactList() != null) {
            userModel.setContactList(user.getContactList().stream()
                    .map(this::mapToUserModel)
                    .collect(Collectors.toList()));
        }
        return userModel;
    }

    @Override
    public UserModel createUser(CreateUserModel user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        userEntity.setEmail(user.getEmail());

        UserEntity savedUser = userRepository.save(userEntity);

        return mapToUserModel(savedUser);
    }

    @Override
    public UserModel getUserByUuid(String uuid) {
        UserEntity userEntity = userRepository.findByUuid(uuid);

        return mapToUserModel(userEntity);
    }

    @Override
    public UserModel createAndAddContact(createNewContact createNewContact) {

        // Create contact User
        UserEntity newContactEntity = new UserEntity();
        newContactEntity.setFirstName(createNewContact.getContactFirstName());
        newContactEntity.setLastName(createNewContact.getContactLastName());
        newContactEntity.setEmail(createNewContact.getContactEmail());
        newContactEntity.setPhoneNumber(createNewContact.getContactPhoneNumber());

        // Persist new contact user
        newContactEntity = userRepository.save(newContactEntity);

        // Add Contact to Our User
        UserEntity currentUser = userRepository.findById(createNewContact.getUserUuid())
                .orElseThrow(() -> new RuntimeException("User not found"));

        currentUser.getContactList().add(newContactEntity);
        currentUser = userRepository.save(currentUser);

        // Fetch the updated user to ensure all changes are reflected
        UserEntity updatedUser = userRepository.findById(currentUser.getUuid())
                .orElseThrow(() -> new RuntimeException("Updated user not found"));

        return mapToUserModel(updatedUser);

//        // create contact User
//        UserModel newContactUser = new UserModel();
//        newContactUser.setFirstName(createNewContact.getContactFirstName());
//        newContactUser.setLastName(createNewContact.getContactLastName());
//        newContactUser.setEmail(createNewContact.getContactEmail());
//        newContactUser.setPhoneNumber(createNewContact.getContactPhoneNumber());
//
//        // Persist new contact user
//        UserEntity newContactEntity = userRepository.createUser(newContactUser);
//
//        // Ensure new contact is fully populated by fetching it from the database
//        UserEntity savedContactEntity = userRepository.findById(newContactEntity.getUuid()).orElseThrow(() -> new RuntimeException("New contact not found"));
//
//        // Add Contact to Our User
//        Optional<UserEntity> userEntity = userRepository.findById(createNewContact.getUserUuid());
//        if (userEntity.isEmpty()) {
//            throw new RuntimeException("User not found");
//        }
//        UserEntity currentUser = userEntity.get();
//        currentUser.getContactList().add(savedContactEntity);
//        userRepository.save(currentUser);
//
//        // Return updated user
//        UserModel returnedCurrentUser = new UserModel();
//        returnedCurrentUser.setFirstName(currentUser.getFirstName());
//        returnedCurrentUser.setLastName(currentUser.getLastName());
//        returnedCurrentUser.setUuid(String.valueOf(currentUser.getUuid()));
//        returnedCurrentUser.setPhoneNumber(currentUser.getPhoneNumber());
//        returnedCurrentUser.setEmail(currentUser.getEmail());
//        returnedCurrentUser.setContactList(currentUser.getContactList().stream()
//                .map(contact -> new UserModel(String.valueOf(contact.getUuid()), contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber(), contact.getEmail()))
//                .collect(Collectors.toList()));
//
//        return returnedCurrentUser;
    }

    @Override
    public UserModel updateUser(UserModel user) {
        UserEntity userEntity = userRepository.findByUuid(user.getUuid());

        if (user.getFirstName() != null) {
            userEntity.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            userEntity.setLastName(user.getLastName());
        }
        if (user.getPhoneNumber() != null) {
            userEntity.setPhoneNumber(user.getPhoneNumber());
        }
        if (user.getEmail() != null) {
            userEntity.setEmail(user.getEmail());
        }
        UserEntity updatedUserEntity = userRepository.save(userEntity);
        return mapToUserModel(updatedUserEntity);
    }


}
