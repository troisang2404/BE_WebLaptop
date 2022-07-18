package fit.nlu.weblaptop.repository;

import fit.nlu.weblaptop.entity.ERole;
import fit.nlu.weblaptop.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(ERole name);
}
