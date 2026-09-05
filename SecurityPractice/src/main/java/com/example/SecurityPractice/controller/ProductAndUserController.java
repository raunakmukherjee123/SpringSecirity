package com.example.SecurityPractice.controller;

import com.example.SecurityPractice.dto.AuthRequest;
import com.example.SecurityPractice.dto.UserResponse;
import com.example.SecurityPractice.model.Product;
import com.example.SecurityPractice.model.UserInfo;
import com.example.SecurityPractice.service.JwtService;
import com.example.SecurityPractice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductAndUserController {

    private final ProductService productService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

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
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest)
    {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if(authenticate.isAuthenticated())
        {
            return jwtService.generateToken(authRequest.getUsername());
        }
        else
        {
            System.out.println("Invalid user or password");
            throw new UsernameNotFoundException("Invalid user request");
        }

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id)
    {
        UserResponse userResponse=productService.getUserById(id);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addProduct(@RequestBody Product product)
    {
        productService.addProduct(product);

        return new ResponseEntity<>("Product has been added",HttpStatus.CREATED);
    }
}
