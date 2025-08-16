package com.example.scms.register;

import com.example.scms.User.User;
import com.example.scms.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import java.util.Map;
import java.util.Objects;

@RestController
public class RegisterController {

    @Autowired
    private UserService svc;

    @PostMapping("/register")
    @CrossOrigin("http://localhost:5173/")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterData d, BindingResult result) {
        // Handle validation errors
        if (result.hasErrors()) {
            // Return first error message as response
            String firstError = Objects.requireNonNull(result.getFieldError()).getDefaultMessage();
            assert firstError != null;
            return ResponseEntity.badRequest().body(Map.of("error", firstError));
        }

        User u = new User();
        u.setUsername(d.getUsername());
        u.setPassword(d.getPassword()); // Will be encoded in service
        u.setEmail(d.getEmail());
        u.setMobile(d.getMobile());

        String error = svc.register(u);
        if (error != null) {
            return ResponseEntity.badRequest().body(Map.of("error", error));
        }

        return ResponseEntity.ok(Map.of("message", "Your account has been registered successfully!"));
    }
}