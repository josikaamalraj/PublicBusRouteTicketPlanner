package com.bus.planner.controller;

import com.bus.planner.dto.*;
import com.bus.planner.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService s;

    public AuthController(AuthService s) {
        this.s = s;
    }

    @PostMapping("/register")
    ResponseEntity<?> register(@Valid @RequestBody RegisterRequest r) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(s.register(r));
    }

    @PostMapping("/login")
    ResponseEntity<?> login(@Valid @RequestBody LoginRequest r) {
        return ResponseEntity.ok(s.login(r));
    }
}