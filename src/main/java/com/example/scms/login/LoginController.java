package com.example.scms.login;

import com.example.scms.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired private UserService svc;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginData d) {
        return svc.authenticate(d.getUsername(), d.getPassword())
                ? ResponseEntity.ok("You have been logged in successfully.")
                : ResponseEntity.status(401).body("Invalid Credentials! Please try again...");
    }
}