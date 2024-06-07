package com.itacademy.RandomDiceRollGameApplication.security;

import com.itacademy.RandomDiceRollGameApplication.model.domain.User;
import com.itacademy.RandomDiceRollGameApplication.security.models.AuthResponse;
import com.itacademy.RandomDiceRollGameApplication.security.models.AuthenticationRequest;
import com.itacademy.RandomDiceRollGameApplication.security.models.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));

    }
    @PostMapping("/authenticate")
    public ResponseEntity <AuthResponse> authenticate(
            @RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authService.authenticate(request));

    }

}
