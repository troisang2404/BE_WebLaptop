package fit.nlu.weblaptop.service;

import fit.nlu.weblaptop.entity.UserEntity;

import java.util.Optional;

public interface UserService{
    Optional<UserEntity> findByUsername(String username); //tim kiem user co ton tai trong db khong?
    UserEntity findOneByUsername(String username);
    Boolean existsByUsername(String username); //username da co trong db chua, khi tao du lieu
    Boolean existsByEmail(String email); //email da co trong db chua
    UserEntity save(UserEntity userEntity);

}
