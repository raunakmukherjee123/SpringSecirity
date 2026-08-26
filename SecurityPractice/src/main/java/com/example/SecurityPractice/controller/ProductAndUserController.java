package com.example.SecurityPractice.controller;

import com.example.SecurityPractice.dto.AuthRequest;
import com.example.SecurityPractice.model.UserInfo;
import com.example.SecurityPractice.service.JwtService;
import com.example.SecurityPractice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductAndUserController {

    private final ProductService productService;

    private final JwtService jwtService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/new/user")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return productService.addUser(userInfo);
    }

    @GetMapping("/hello")
    @PreAuthorize("hasRole('ADMIN')")
    public String hello() {
        return "Hello";
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(AuthRequest authRequest)
    {
        return jwtService.generateToken(authRequest.getUsername());
    }
}
