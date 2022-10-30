package com.server.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name="product",schema = "backend")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer productId;

    private Integer userId;

    private String name;

    private String category;

    private String location;

    private double price;

    private Integer quantity;

    private String imglocation;

    public void setUserID(Integer userId) {
        this.userId = userId;
    }
}
