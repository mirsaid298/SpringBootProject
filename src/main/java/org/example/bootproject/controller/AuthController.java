package org.example.bootproject.controller;

import jakarta.validation.Valid;
import org.example.bootproject.RegisterDTO;
import org.example.bootproject.entity.Role;
import org.example.bootproject.entity.User;
import org.example.bootproject.repository.RoleRepository;
import org.example.bootproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Optional;
import java.util.Set;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model) {
        RegisterDTO registerDTO = new RegisterDTO();
        model.addAttribute("registerDTO", registerDTO);
        model.addAttribute("success", false);
        return "register";
    }

    @PostMapping("/register")
    public String register(
            Model model,
            @Valid @ModelAttribute RegisterDTO registerDTO,
            BindingResult bindingResult) {
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            bindingResult.addError(
                    new FieldError("registerDTO", "confirmPassword",
                            "Passwords do not match")
            );
        }

        Optional<User> user = userRepository.findByUsername(registerDTO.getUsername());
        if (user.isPresent()) {
            bindingResult.addError(new FieldError("registerDTO", "username",
                    "Username is already in use")
            );
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            User newUser = new User();

            Role role = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Role not found"));

            newUser.setName(registerDTO.getName());
            newUser.setUsername(registerDTO.getUsername());
            newUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
            newUser.setRoles(Set.of(role));
            userRepository.save(newUser);

            model.addAttribute("registerDTO", new RegisterDTO());
            model.addAttribute("success", true);
        } catch (Exception e) {
            bindingResult.addError(
                    new FieldError("registerDTO", "name", e.getMessage())
            );
        }

        return "register";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
