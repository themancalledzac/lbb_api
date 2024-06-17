package LittleBlackBookApi.repository;

import LittleBlackBookApi.entity.UserEntity;
import LittleBlackBookApi.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> createUser(UserModel user);
}
