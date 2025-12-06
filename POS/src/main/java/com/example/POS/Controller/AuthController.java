//package com.example.POS.Controller;
//
//import com.example.POS.Model.*;
//import com.example.POS.Repository.*;
//import com.example.POS.Security.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.*;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.Instant;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    @Autowired private AuthenticationManager authenticationManager;
//    @Autowired private UserRepository userRepository;
//    @Autowired private RefreshTokenRepository refreshTokenRepository;
//    @Autowired private PasswordEncoder passwordEncoder;
//    @Autowired private JwtUtil jwtUtil;
//
//    // Register
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody User user) {
//        if (userRepository.existsByEmail(user.getEmail()))
//            return ResponseEntity.badRequest().body(Map.of("error", "Email already in use"));
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        if (user.getRole() == null) user.setRole(User.Role.USER);
//        User saved = userRepository.save(user);
//        return ResponseEntity.ok(Map.of("message", "Registered", "userId", saved.getId()));
//    }
//
//    // Login -> returns access + refresh
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User loginRequest) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
//        );
//
//        String accessToken = jwtUtil.generateAccessToken(loginRequest.getEmail());
//        String refreshTokenStr = jwtUtil.generateRefreshToken(loginRequest.getEmail());
//
//        RefreshToken refreshToken = new RefreshToken();
//        refreshToken.setToken(refreshTokenStr);
//        refreshToken.setUser(userRepository.findByEmail(loginRequest.getEmail()).get());
//        refreshToken.setExpiryDate(Instant.now().plusMillis(1000L * 60 * 60 * 24 * 7));
//        refreshTokenRepository.save(refreshToken);
//
//        return ResponseEntity.ok(Map.of(
//                "accessToken", accessToken,
//                "refreshToken", refreshTokenStr
//        ));
//    }
//
//    // Refresh
//    @PostMapping("/refresh")
//    public ResponseEntity<?> refresh(@RequestBody Map<String, String> body) {
//        String refreshTokenStr = body.get("refreshToken");
//        var opt = refreshTokenRepository.findByToken(refreshTokenStr);
//        if (opt.isEmpty()) return ResponseEntity.status(401).body(Map.of("error", "Invalid refresh token"));
//
//        RefreshToken rt = opt.get();
//        if (rt.getExpiryDate().isBefore(Instant.now())) {
//            refreshTokenRepository.delete(rt);
//            return ResponseEntity.status(401).body(Map.of("error", "Refresh token expired"));
//        }
//
//        String email = jwtUtil.extractEmail(refreshTokenStr);
//        String newAccess = jwtUtil.generateAccessToken(email);
//        String newRefresh = jwtUtil.generateRefreshToken(email);
//
//        // replace stored refresh token
//        rt.setToken(newRefresh);
//        rt.setExpiryDate(Instant.now().plusMillis(1000L * 60 * 60 * 24 * 7));
//        refreshTokenRepository.save(rt);
//
//        return ResponseEntity.ok(Map.of("accessToken", newAccess, "refreshToken", newRefresh));
//    }
//
//    // Logout (invalidate refresh tokens)
//    @PostMapping("/logout")
//    public ResponseEntity<?> logout(@RequestBody Map<String, String> body) {
//        String email = body.get("email");
//        var userOpt = userRepository.findByEmail(email);
//        if (userOpt.isEmpty()) return ResponseEntity.badRequest().body(Map.of("error", "User not found"));
//
//        int deleted = refreshTokenRepository.deleteByUser(userOpt.get());
//        return ResponseEntity.ok(Map.of("message", "Logged out", "deletedRefreshTokens", deleted));
//    }
//}
