package APC.example.Eventra.controller;

import APC.example.Eventra.dto.AuthResponse;
import APC.example.Eventra.dto.LoginRequest;
import APC.example.Eventra.dto.SignupRequest;
import APC.example.Eventra.model.User;
import APC.example.Eventra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignupRequest signupRequest) {
        try {
            // Check if user already exists
            if (userService.existsByEmail(signupRequest.getEmail())) {
                return ResponseEntity.badRequest()
                        .body(new AuthResponse(false, "Email already registered"));
            }

            // Create new user
            User user = new User(
                    signupRequest.getEmail(),
                    passwordEncoder.encode(signupRequest.getPassword()),
                    signupRequest.getFirstName(),
                    signupRequest.getLastName()
            );

            User savedUser = userService.save(user);

            return ResponseEntity.ok(new AuthResponse(
                    true,
                    "User registered successfully",
                    savedUser.getId(),
                    savedUser.getEmail(),
                    savedUser.getFirstName(),
                    savedUser.getLastName()
            ));

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new AuthResponse(false, "Registration failed: " + e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            Optional<User> userOptional = userService.findByEmail(loginRequest.getEmail());
            
            if (userOptional.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(new AuthResponse(false, "Invalid email or password"));
            }

            User user = userOptional.get();
            
            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return ResponseEntity.badRequest()
                        .body(new AuthResponse(false, "Invalid email or password"));
            }

            return ResponseEntity.ok(new AuthResponse(
                    true,
                    "Login successful",
                    user.getId(),
                    user.getEmail(),
                    user.getFirstName(),
                    user.getLastName()
            ));

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new AuthResponse(false, "Login failed: " + e.getMessage()));
        }
    }

    @GetMapping("/test")
    public ResponseEntity<Map<String, Object>> test() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Auth API is working!");
        response.put("timestamp", System.currentTimeMillis());
        response.put("endpoints", Map.of(
                "signup", "POST /auth/signup",
                "login", "POST /auth/login",
                "test", "GET /auth/test"
        ));
        response.put("registeredUsers", userService.findAll().size());
        
        return ResponseEntity.ok(response);
    }
}