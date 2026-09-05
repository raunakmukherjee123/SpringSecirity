package com.example.SecurityPractice.service;

import com.example.SecurityPractice.dto.UserResponse;
import com.example.SecurityPractice.model.Product;
import com.example.SecurityPractice.model.UserInfo;
import com.example.SecurityPractice.projection.UserProjection;
import com.example.SecurityPractice.repository.ProductRepository;
import com.example.SecurityPractice.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductRepository productRepository;

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);

        return "User has been added";
    }

    public UserResponse getUserById(Integer id) {
        UserProjection userProjection=userInfoRepository.getUserById(id);

        UserResponse userResponse=UserResponse.builder()
                .name(userProjection.getName())
                .email(userProjection.getEmail())
                .role(userProjection.getRoles())
                .build();

        return userResponse;
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }
}
