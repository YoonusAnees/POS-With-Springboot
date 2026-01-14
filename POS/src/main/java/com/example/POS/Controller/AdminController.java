package com.example.POS.Controller;

import com.example.POS.Model.User;
import com.example.POS.Repository.UserRepository;
import com.example.POS.exception.ResourceNotFoundException;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@Getter
@Setter
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    // Admin can see all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Admin can delete any user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
        User deleteUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with id: " + id));
        userRepository.delete(deleteUser);
        return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
    }

    // Admin can update any user
    @PutMapping("/users/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable Long id, @RequestBody User user) {
        User userUpdate = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with id: " + id));

        if (user.getFirstName() != null) userUpdate.setFirstName(user.getFirstName());
        if (user.getLastName() != null) userUpdate.setLastName(user.getLastName());
        if (user.getEmail() != null) userUpdate.setEmail(user.getEmail());
        if (user.getPassword() != null) userUpdate.setPassword(user.getPassword());
        if (user.getRole() != null) userUpdate.setRole(user.getRole()); // admin can change role

        userRepository.save(userUpdate);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User updated successfully");
        response.put("user", userUpdate);

        return ResponseEntity.ok(response);
    }
}
