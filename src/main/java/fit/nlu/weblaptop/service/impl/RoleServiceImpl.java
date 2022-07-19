package fit.nlu.weblaptop.service.impl;

import fit.nlu.weblaptop.entity.ERole;
import fit.nlu.weblaptop.entity.RoleEntity;
import fit.nlu.weblaptop.repository.RoleRepository;
import fit.nlu.weblaptop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<RoleEntity> findByName(ERole name) {
        return roleRepository.findByName(name);
    }
}
