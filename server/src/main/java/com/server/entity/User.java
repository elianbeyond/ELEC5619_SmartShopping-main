package com.server.entity;

import lombok.Data;

import javax.persistence.*;



@Entity
@Table(name="user",schema = "backend")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Integer id;

    private Integer productId;

    private Integer voucherId;

    private String username;

    private String password;

    private String userEmail;

    private String address;

    private boolean admin;

    private boolean isBan;



}
