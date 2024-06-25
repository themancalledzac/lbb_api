package LittleBlackBookApi.repository;

import LittleBlackBookApi.entity.UserEntity;
import LittleBlackBookApi.model.UserModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public UserEntity createUser(UserModel user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        entityManager.persist(userEntity);
        return userEntity;
    }
}