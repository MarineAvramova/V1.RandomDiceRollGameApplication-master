package com.itacademy.RandomDiceRollGameApplication.security;

import com.itacademy.RandomDiceRollGameApplication.model.domain.User;
import com.itacademy.RandomDiceRollGameApplication.security.models.AuthResponse;
import com.itacademy.RandomDiceRollGameApplication.security.models.AuthenticationRequest;
import com.itacademy.RandomDiceRollGameApplication.security.models.RegisterRequest;

public interface AuthService {
    AuthResponse register (RegisterRequest request);
    AuthResponse authenticate  (AuthenticationRequest request);
}
