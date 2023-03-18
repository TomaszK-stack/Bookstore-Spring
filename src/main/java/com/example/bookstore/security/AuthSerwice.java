package com.example.bookstore.security;

import com.example.bookstore.entities.Cart;
import com.example.bookstore.entities.User;
import com.example.bookstore.repositories.CartRepository;
import com.example.bookstore.repositories.UserRepository;
import com.example.bookstore.requests.AuthRequest;
import com.example.bookstore.requests.AuthResponse;
import com.example.bookstore.requests.RegisterRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthSerwice {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userDao;

    private final PasswordEncoder passwordEncoder;

    private final CartRepository cartRepository;


    @Autowired
    public AuthSerwice(JwtService jwtService, AuthenticationManager authenticationManager, UserRepository userDao, PasswordEncoder passwordEncoder, CartRepository cartRepository) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.cartRepository = cartRepository;
    }

    public boolean register_walidace(RegisterRequest request) {
        User f_by_email = userDao.findByEmail(request.getEmail()).orElse(null);
        User f_by_username = userDao.findByUsername(request.getUsername()).orElse(null);
        if (f_by_email == null && f_by_username == null) return true;
        else return false;
    }

    public ResponseEntity<String> register(RegisterRequest request) {
        if (register_walidace(request)) {

            var user = User.builder()
                    .email(request.getEmail())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();
            try {
                userDao.save(user);
                var cart = Cart.builder()
                        .user(user)
                        .build();
                cartRepository.save(cart);


            } catch (ConstraintViolationException e) {
                return ResponseEntity.status(435).body("Invalid data");
            }
            return ResponseEntity.ok("Registration succed");


        }
        return ResponseEntity.status(435).body("Invalid data");

    }

    public AuthResponse authenticate(AuthRequest request) {

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())

            );
        } catch (AuthenticationException e) {
            return null;
        }
        Object user = null;
        user = userDao.findByEmail(request.getEmail()).orElseThrow();
        var jwt_token = jwtService.generateToken((UserDetails) user);
        return AuthResponse.builder().
                token(jwt_token).
                build();

    }


}
