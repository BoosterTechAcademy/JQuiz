package com.example.Login.Controller;

import com.example.Login.dto.AuthResponse;
import com.example.Login.dto.LoginRequest;
import com.example.Login.dto.SignupRequest;
import com.example.Login.service.AuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public AuthResponse signup(@RequestBody SignupRequest request) {
        String token = authService.signup(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );
        return new AuthResponse(token);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        String token = authService.login(
                request.getEmail(),
                request.getPassword()
        );
        return new AuthResponse(token);
    }

    @GetMapping("hello")
    public String helloController(){

        return "yes signed as Jeya";
    }
}
