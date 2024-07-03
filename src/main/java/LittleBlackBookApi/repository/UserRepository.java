package LittleBlackBookApi.repository;

import LittleBlackBookApi.entity.UserEntity;
import LittleBlackBookApi.model.CreateUserModel;
import LittleBlackBookApi.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String>, CustomUserRepository {

    Optional<UserEntity> findByUuid(String uuid);
}
