package com.server.entity.vo;

import com.server.entity.Product;
import lombok.Data;

@Data
public class ProductVO extends Product {

    private String supplier;
}