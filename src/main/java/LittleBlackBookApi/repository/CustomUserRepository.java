package LittleBlackBookApi.repository;

import LittleBlackBookApi.entity.UserEntity;
import LittleBlackBookApi.model.UserModel;

public interface CustomUserRepository {
    UserEntity createUser(UserModel user);
}
