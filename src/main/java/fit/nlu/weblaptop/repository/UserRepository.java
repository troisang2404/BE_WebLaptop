package fit.nlu.weblaptop.repository;

import fit.nlu.weblaptop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username); //tim kiem user co ton tai trong db khong?
    Boolean existsByUsername(String username); //username da co trong db chua, khi tao du lieu
    Boolean existsByEmail(String email); //email da co trong db chua
}