package com.example.orderservice.dto;

import lombok.Data;

@Data
public class Order
{
    private String product;
    private Integer quantity;
}
