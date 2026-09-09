package com.example.SecurityPractice.service;

import com.example.SecurityPractice.dto.PageResponse;
import com.example.SecurityPractice.dto.ProductResponse;
import com.example.SecurityPractice.dto.UserResponse;
import com.example.SecurityPractice.exception.ProductNotFoundException;
import com.example.SecurityPractice.model.Product;
import com.example.SecurityPractice.model.UserInfo;
import com.example.SecurityPractice.projection.ProductProjection;
import com.example.SecurityPractice.projection.UserProjection;
import com.example.SecurityPractice.repository.ProductRepository;
import com.example.SecurityPractice.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ProductResponse getProductById(Integer id) {
        ProductProjection productProjection=productRepository.findProductById(id);

        if(productProjection==null)
        {
            throw new ProductNotFoundException("No product found of id = "+id);
        }

        ProductResponse productResponse=ProductResponse.builder()
                .name(productProjection.getName())
                .price(productProjection.getPrice())
                .qty(productProjection.getQty())
                .build();

        return productResponse;
    }

    public PageResponse<?> findAllProducts(int pageNo, int pageSize, String sortBy) {
        Pageable pageable= PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Page<Product> productPage=productRepository.findAll(pageable);
        List<Product> products=productPage.getContent();

        return PageResponse.<List<Product>>builder()
                .totalPages(productPage.getTotalPages())
                .content(products)
                .isLastPage(productPage.isLast())
                .pageNumber(productPage.getNumber())
                .pageSize(productPage.getSize())
                .totalElements(productPage.getTotalElements())
                .build();
    }
}
