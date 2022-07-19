package fit.nlu.weblaptop.service;

import fit.nlu.weblaptop.entity.ERole;
import fit.nlu.weblaptop.entity.RoleEntity;

import java.util.Optional;

public interface RoleService {
    Optional<RoleEntity> findByName(ERole name);
}
