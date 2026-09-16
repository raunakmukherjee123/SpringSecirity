package com.example.SecurityPractice.dto;

import lombok.Data;

@Data
public class ProductRequest {

    private String name;

    private int qty;

    private double price;
}
