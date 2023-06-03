package com.google.demo.Service;

import com.google.demo.Entity.RoleEntity;
import com.google.demo.Enum.RoleEnum;
import com.google.demo.Repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleEntity create(RoleEntity entity) {
        entity.setRole(RoleEnum.MEMBER.toString());
        return roleRepository.save(entity);
    }


    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }

    public RoleEntity getRoleById(Long id) {
        return roleRepository.getById(id);
    }

    public RoleEntity update(Long id, RoleEntity roleEntity) {
        RoleEntity existingRole = roleRepository.getById(id);
        existingRole.setRole(roleEntity.getRole());
        return roleRepository.save(existingRole);
    }

    public void delete(RoleEntity roleEntity) {
        roleRepository.delete(roleEntity);
    }
}
