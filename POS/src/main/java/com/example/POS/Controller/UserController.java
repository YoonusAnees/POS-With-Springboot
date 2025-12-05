package com.example.POS.Controller;

import com.example.POS.Model.User;
import com.example.POS.Repository.UserRepository;
import com.example.POS.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Get own user details
    @GetMapping("/me")
    public User getMe(Authentication authentication) {
        String email = authentication.getName(); // Spring Security sets email as principal
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + email));
    }

    // Update own profile
    @PutMapping("/me")
    public ResponseEntity<Map<String, Object>> updateMe(Authentication authentication, @RequestBody User user) {
        String email = authentication.getName();
        User userUpdate = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + email));

        if (user.getFirstName() != null) userUpdate.setFirstName(user.getFirstName());
        if (user.getLastName() != null) userUpdate.setLastName(user.getLastName());
        if (user.getPassword() != null) userUpdate.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(userUpdate);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Profile updated successfully");
        response.put("user", userUpdate);

        return ResponseEntity.ok(response);
    }
}
