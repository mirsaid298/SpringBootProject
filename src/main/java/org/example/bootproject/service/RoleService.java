package org.example.bootproject.service;

import org.example.bootproject.entity.Role;
import org.example.bootproject.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRoleIfNotExist(String name) {
        return roleRepository.findByName(name).orElseGet(() -> {
            Role role = new Role(name);
            return roleRepository.save(role);
        });
    }

    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

}
