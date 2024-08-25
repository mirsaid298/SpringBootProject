package org.example.bootproject;

import jakarta.annotation.PostConstruct;
import org.example.bootproject.entity.User;
import org.example.bootproject.repository.UserRepository;
import org.example.bootproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        roleService.createRoleIfNotExist("ROLE_ADMIN");
        roleService.createRoleIfNotExist("ROLE_USER");

        createDefaultAdmin();
    }

    private void createDefaultAdmin() {

        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRoles(Set.of(roleService.findByName("ROLE_ADMIN")
                    .orElseThrow(() -> new RuntimeException("Role ROLE_ADMIN not found"))));

            userRepository.save(admin);
        }
    }
}
