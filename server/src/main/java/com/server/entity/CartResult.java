package com.server.entity;

import lombok.Data;

@Data
public class CartResult {
    private  Integer cartId;
    private String productName;
    private double price;
    private Integer productId;

    private Integer quantity;
}
