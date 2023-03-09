package com.example.bookstore.requests;

import com.example.bookstore.entities.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String email;
    private String password;
    private String username;
    private Roles roles;




}
