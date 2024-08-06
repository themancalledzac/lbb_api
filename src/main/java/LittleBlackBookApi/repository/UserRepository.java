package LittleBlackBookApi.repository;

import LittleBlackBookApi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String>, CustomUserRepository {

    UserEntity findByUuid(String uuid);
}
