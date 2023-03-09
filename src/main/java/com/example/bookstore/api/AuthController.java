package com.example.bookstore.api;

import com.example.bookstore.requests.AuthRequest;
import com.example.bookstore.requests.AuthResponse;
import com.example.bookstore.requests.RegisterRequest;
import com.example.bookstore.security.AuthSerwice;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@Builder
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    @Autowired
    AuthSerwice authSerwice;

    @Autowired
    AuthenticationManager authenticationManager;




    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){

        return authSerwice.register(request);


    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        AuthResponse ar = authSerwice.authenticate(request);
        if (ar != null) {
            return ResponseEntity.ok(ar);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }


}
