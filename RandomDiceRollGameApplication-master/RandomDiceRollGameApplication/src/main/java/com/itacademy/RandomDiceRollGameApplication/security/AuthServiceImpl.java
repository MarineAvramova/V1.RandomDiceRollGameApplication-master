package com.itacademy.RandomDiceRollGameApplication.security;

import com.itacademy.RandomDiceRollGameApplication.exceptions.UserAlreadyExists;
import com.itacademy.RandomDiceRollGameApplication.exceptions.UserNotFound;
import com.itacademy.RandomDiceRollGameApplication.model.domain.Role;
import com.itacademy.RandomDiceRollGameApplication.model.domain.User;
import com.itacademy.RandomDiceRollGameApplication.model.repository.UserRepository;
import com.itacademy.RandomDiceRollGameApplication.security.models.AuthResponse;
import com.itacademy.RandomDiceRollGameApplication.security.models.AuthenticationRequest;
import com.itacademy.RandomDiceRollGameApplication.security.models.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {

        //log.info("Registering user with email: {}", request.getEmail());
//        if (userRepository.findUserByEmail(request.getEmail()).isPresent()) {
//            throw new RuntimeException("Email already exists");
//        }
//        if(userAlreadyExists(request.getName())){
//            log.warn("User already exists: {}", request.getName());
//            throw new UserAlreadyExists("User already exists.");
//        }
//
//        User user = User.builder()
//                .name(request.getName().isBlank() ? "ANONYMOUS" : request.getName())
//                .userEmail(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role (Role.USER)
////                         .role(request.getName().isBlank() ? Role.ANONYMOUS :
////                                (request.getName().startsWith("admin") ? Role.ADMIN : Role.USER))
//                .build();
////        if (request.getName().equalsIgnoreCase("ADMIN")){
////            user.setRole(Role.ADMIN);
////        }
//        log.info("User registered successfully: {}", user.getName());
//        return userRepository.save(user);
        if (userAlreadyExists(request.getName())) {
            throw new UserAlreadyExists("User already exists.");
        }
        User user = User.builder()
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        //var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken).build();
    }

    @Override
    public AuthResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getName(),
                        request.getPassword()
                )
        );
        User user = userRepository.findUserByName(request.getName()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken).build();
    }


//    @Override
//    public AuthResponse authenticate(AuthenticationRequest request) {
//        log.info("Authenticating user: {}", request.getEmail());
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//        User user = userRepository.findUserByEmail(request.getEmail()).orElseThrow(()
//                -> new UserNotFound("User not found with email: " + request.getEmail()));
//
//        String jwtToken = jwtService.generateToken(user);
//        return AuthResponse.builder()
//                .token(jwtToken).build();
//    }

    private boolean userAlreadyExists(String username) {

        return userRepository.existsByNameIgnoreCase(username);
    }
}


